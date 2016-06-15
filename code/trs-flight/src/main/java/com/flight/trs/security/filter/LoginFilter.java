package com.flight.trs.security.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flight.trs.model.entity.Customer;
import com.flight.trs.security.token.CustomUsernamePasswordTocken;
import com.flight.trs.service.basic.ICustomerService;
import com.flight.trs.util.RandmSaltGenerator;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月20日 下午9:54:52
 * @version V1.0
 */
public class LoginFilter extends PathMatchingFilter {

	@Resource
	private ICustomerService customerService;
	private String loginUrl = "/login";
	private String successUrl = "/";
	Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
	@Override  
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {  
		if(SecurityUtils.getSubject().isAuthenticated()) {  
			//已经登录过
            return true;  
        }  
        HttpServletRequest req = (HttpServletRequest) request;  
        HttpServletResponse resp = (HttpServletResponse) response;  
        //logger.info("未登录");
        if(isLoginRequest(req)) { 
            if("post".equalsIgnoreCase(req.getMethod())) {//form表单提交  
            	//logger.info("为登录请求");
                boolean loginSuccess = doLogin(req); //登录  
                if(loginSuccess) {  
                	//logger.info("登录成功");
                	return redirectToSuccessUrl(req, resp);  
                }  
            }
            return true;//继续过滤器链  
        } else {
        	//保存当前地址并重定向到登录界面  
            saveRequestAndRedirectToLogin(req, resp);  
            //logger.info("保存当前地址并重定向");
            return false;  
        }  
    }  
	
    private boolean doLogin(HttpServletRequest req) {  
    	
    	UsernamePasswordToken token = createToken(req);
    	Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);	//认证
		} catch (Exception e) {  
            req.setAttribute("shiroLoginFailure", e.getClass());  
            return false;  
        }  
		
		//认证成功后，刷新当前用于数据库中的动态盐
		String dynamicSalt = RandmSaltGenerator.nextSalt();
		long custId = Long.parseLong(currentUser.getPrincipal().toString());
		Customer cust = customerService.findByID(custId);
		cust.setDynamicSalt(dynamicSalt);
		customerService.update(cust);
	
		Session session = currentUser.getSession();
		session.setAttribute("cust", cust);
		session.setAttribute("username", token.getUsername());
		
		return true; 
    }  
    
    public UsernamePasswordToken createToken(HttpServletRequest req){
    	String username = req.getParameter("username"); 
		String password = req.getParameter("password"); 
		String captcha = req.getParameter("captcha"); 
		return new CustomUsernamePasswordTocken(username, password, captcha);
    }
    
    //该请求是否为登录请求
    private boolean isLoginRequest(HttpServletRequest req) {  
        return pathsMatch(loginUrl, WebUtils.getPathWithinApplication(req));  
    }  

    private boolean redirectToSuccessUrl(HttpServletRequest req, HttpServletResponse resp) throws IOException {  
        WebUtils.redirectToSavedRequest(req, resp, successUrl);  
        return false;  
    }  
    
    private void saveRequestAndRedirectToLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {  
        WebUtils.saveRequest(req);  
        WebUtils.issueRedirect(req, resp, loginUrl);  
    }  
    
    public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}
    
}
