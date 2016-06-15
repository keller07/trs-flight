package com.flight.trs.controller.account;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.flight.trs.controller.BaseController;
import com.flight.trs.model.entity.Customer;
import com.flight.trs.model.info.LoginInfo;
import com.flight.trs.security.token.CustomUsernamePasswordTocken;
import com.flight.trs.security.token.IncorrectCaptchaException;
import com.flight.trs.service.basic.ICustomerService;
import com.flight.trs.util.CaptchaBuilder;
import com.flight.trs.util.RandmSaltGenerator;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月18日 下午6:12:37
 * @version V1.0
 */
@Controller
@RequestMapping("/")
@SessionAttributes({"cstmID", "loginName"})
public class LoginController 
	extends BaseController
	{

	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource
	private ICustomerService customerService;
	
	//--------------------------------------------------
	//-------------- Customer login --------------------
	//--------------------------------------------------
	
	@RequestMapping("/login")
	public ModelAndView login(ModelAndView modelAndView){
		modelAndView.setViewName("customer/login");
		return modelAndView;
	}
	
	@RequestMapping("/logintest")
	public ModelAndView logintest(ModelAndView modelAndView){
		modelAndView.setViewName("customer/index");
		return modelAndView;
	}
	
	//获取验证码
	@RequestMapping(value = "/login.getCaptchaImage")
	public void getCaptchaImage() {
		response.setHeader("Pragma","No-cache");     
        response.setHeader("Cache-Control","no-cache");     
        response.setDateHeader("Expires", 0);     
        response.setContentType("image/jpeg");			//表明生成的响应是图片     
               
        String captcha = CaptchaBuilder.getCaptcha(4);	//生成4位的随机验证码    
        
        int width = 120;	// 宽100像素
        int height = 37;	// 高18像素
        BufferedImage image = CaptchaBuilder.getCaptchaImage(width, height, captcha);
         
        session.setAttribute("captcha",captcha);     
        try {
        	//以图片的方式向客户端输出
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/login.getSalts",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getSalts(@RequestParam("username") String username) throws ServletException{
		
		Map<String, String> result = new HashMap<String, String>();
		
		if ( username == null || username.trim().equals("")) {
			result.put("status", "error");
			result.put("message", "获取用户名失败！");
			logger.error("get requestParam failed");
			return result;
		}
		
		Customer customer = null;
		try {
			customer = customerService.verify(username);
		} catch (IllegalArgumentException e) {
			result.put("status", "error");
			result.put("message", "用户名格式不对！");
			return result;
		}
		
		String staticSalt = "";
		String dynamicSalt = "";
		if (customer != null) {
			//如果用户名对应用户存在则返回数据库中该用户账户对应的随机盐，
			staticSalt = customer.getStaticSalt();
			dynamicSalt = customer.getDynamicSalt();
		}else{
			//否则返回新生成的随机盐
			staticSalt = RandmSaltGenerator.nextSalt();
			dynamicSalt = RandmSaltGenerator.nextSalt();
		}
		
		result.put("status", "succeed");	
		result.put("staticSalt", staticSalt);
		result.put("dynamicSalt", dynamicSalt);
		return result;
	}
	
//	@RequestMapping(value="/login.do",method=RequestMethod.POST)
//	public Map<String, String> doLogin(LoginInfo logininfo){
//		
//		//登录信息获取失败
//		Map<String, String> result = new HashMap<String, String>();
//		if (logininfo == null) {
//			result.put("status", "error");
//			result.put("message", "loginInfoLost");
//			return result;
//		}
//		
//		String username1 = request.getParameter("username");  
//		if (null!=username1) {
//			logger.info("不为空："+username1);
//		}
//		Subject currentUser = SecurityUtils.getSubject();
//		
//		String username = logininfo.getUsername();
//		String password = logininfo.getPassword();
//		String captcha = logininfo.getCaptcha();
//		//用提交的用户名和密码生成token
//		CustomUsernamePasswordTocken token 
//		= new CustomUsernamePasswordTocken(username, password, captcha);
//		
//		try {
//			
//			currentUser.login(token);	//认证
//			
//		}catch (IncorrectCaptchaException e){
//			// 验证码不正确
//			result.put("status", "failed");
//			result.put("message", "incorrectCaptcha");
//			return result;
//		}catch (UnknownAccountException e) {
//			// 找不到该账户信息
//			result.put("status", "failed");
//			result.put("message", "incorrectUsernameOrPassword");
//			return result;
//		}catch (IncorrectCredentialsException e) {
//			// 找到账户但密码不必配
//			result.put("status", "failed");
//			result.put("message", "incorrectUsernameOrPassword");
//			return result;
//		}catch (LockedAccountException e) {
//			// 找到账户但该账户处于锁定状态
//			result.put("status", "failed");
//			result.put("message", "lockedAccount");
//			return result;
//		}catch (DisabledAccountException e) {
//			// 找到账户但该账户处于被删除状态
//			result.put("status", "failed");
//			result.put("message", "disabledAccount");
//			return result;
//		}
//		
//		//认证成功后，刷新当前用于数据库中的动态盐
//		String dynamicSalt = RandmSaltGenerator.nextSalt();
//		long custId = Long.parseLong(currentUser.getPrincipal().toString());
//		Customer cust = customerService.findByID(custId);
//		cust.setDynamicSalt(dynamicSalt);
//		customerService.update(cust);
//		
//		Session session = currentUser.getSession();
//		session.setAttribute("cust", cust);
//		session.setAttribute("username", username);
//		result.put("status", "succeed");
//		
//		return result;
//		
//	}
	
	
//	@RequestMapping(value="/login.do",method=RequestMethod.POST)
//	public ModelAndView doLogin(LoginInfo logininfo, ModelAndView modelAndView){
//		
//		//登录信息获取失败
//		Map<String, String> result = new HashMap<String, String>();
//		if (logininfo == null) {
//			modelAndView.setViewName("customer/login");
//			modelAndView.addObject("status", "error");
//			modelAndView.addObject("message", "loginInfoLost");
//			return modelAndView;
//		}
//		
//		Subject currentUser = SecurityUtils.getSubject();
//		
//		String username = logininfo.getUsername();
//		String password = logininfo.getPassword();
//		String captcha = logininfo.getCaptcha();
//		//用提交的用户名和密码生成token
//		CustomUsernamePasswordTocken token 
//		= new CustomUsernamePasswordTocken(username, password, captcha);
//		
//		try {
//			
//			currentUser.login(token);	//认证
//			
//		}catch (IncorrectCaptchaException e){
//			// 验证码不正确
//			modelAndView.addObject("status", "failed");
//			modelAndView.addObject("message", "incorrectCaptcha");
//			modelAndView.setViewName("customer/login");
//			return modelAndView;
//		}catch (UnknownAccountException e) {
//			// 找不到该账户信息
//			modelAndView.addObject("status", "failed");
//			modelAndView.addObject("message", "incorrectUsernameOrPassword");
//			modelAndView.setViewName("customer/login");
//			return modelAndView;
//		}catch (IncorrectCredentialsException e) {
//			// 找到账户但密码不必配
//			modelAndView.addObject("status", "failed");
//			modelAndView.addObject("message", "incorrectUsernameOrPassword");
//			modelAndView.setViewName("customer/login");
//		}catch (LockedAccountException e) {
//			// 找到账户但该账户处于锁定状态
//			modelAndView.addObject("status", "failed");
//			modelAndView.addObject("message", "lockedAccount");
//			modelAndView.setViewName("customer/login");
//		}catch (DisabledAccountException e) {
//			// 找到账户但该账户处于被删除状态
//			modelAndView.addObject("status", "failed");
//			modelAndView.addObject("message", "disabledAccount");
//			modelAndView.setViewName("customer/login");
//		}
//		
//		//认证成功后，刷新当前用于数据库中的动态盐
//		String dynamicSalt = RandmSaltGenerator.nextSalt();
//		long custId = Long.parseLong(currentUser.getPrincipal().toString());
//		Customer cust = customerService.findByID(custId);
//		cust.setDynamicSalt(dynamicSalt);
//		customerService.update(cust);
//		
//		Session session = currentUser.getSession();
//		session.setAttribute("cust", cust);
//		session.setAttribute("username", username);
//		result.put("status", "succeed");
//		modelAndView.setViewName("index");
//		return modelAndView;
//	}

	@RequestMapping("/logout")
	public ModelAndView logout(ModelAndView modelAndView){
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		modelAndView.setViewName("customer/login");
		return modelAndView;
	}
	
	@RequestMapping(value="/cust/resetPassword",method=RequestMethod.POST)
	public ModelAndView changePassword(ModelAndView modelAndView,String oldPassword, String newPassword){
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			//
		};
		long principal = (long)currentUser.getPrincipal();
		Customer customer = customerService.findByID(principal);
		if (null == customer) {
			//
		}
		String staticSalt = customer.getStaticSalt();
		String password = new SimpleHash("MD5", newPassword).toHex();
		password = new SimpleHash("MD5", password, staticSalt, 1).toHex();
		customer.setPassword(password);
		//刷新用于认证的动态盐
		String newDynamicSalt = RandmSaltGenerator.nextSalt();
		customer.setDynamicSalt(newDynamicSalt);
		customerService.update(customer);
		currentUser.logout();
		modelAndView.setViewName("customer/login");
		return modelAndView;
	}
	
	
	//--------------------------------------------------
	//-------------- Customer register -----------------
	//--------------------------------------------------
	
//	//处理记住登录状态
//	public void keepLoggedin(String keepLoggedIn, String phone, String password){
//		if (keepLoggedIn.equals("true")) {
//			Cookie cookiePhone = new Cookie("phone",phone);
//			Cookie cookiePassword = new Cookie("password",password);
//			cookiePhone.setMaxAge(604800);		//设置有效期一周
//			cookiePassword.setMaxAge(604800);
//			response.addCookie(cookiePhone);	//保存(覆盖)
//			response.addCookie(cookiePassword);
//		}
//		else {
//			Cookie[] cookies = request.getCookies();
//			if (cookies != null  &&  cookies.length>0) {
//				for (Cookie c:cookies) {
//					if (c.getName().equals("phone") || c.getName().equals("password")) {
//						c.setMaxAge(0);			//设置过期
//						response.addCookie(c);	//重新保存
//					}
//				}
//			}
//		}
//	}

}
