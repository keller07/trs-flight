package com.flight.trs.controller.account;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flight.trs.controller.BaseController;
import com.flight.trs.model.entity.Customer;
import com.flight.trs.model.info.RegisterInfo;
import com.flight.trs.service.basic.ICustomerService;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月4日 下午5:00:36
 * @version V1.0
 */
@Controller
@RequestMapping("/")
public class RegisterController extends BaseController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource
	private ICustomerService customerService;
	
	SecureRandomNumberGenerator random = new SecureRandomNumberGenerator();
	
	DefaultHashService hashService = new DefaultHashService();
	
	@RequestMapping(value="/register.do",method=RequestMethod.POST)
	public Map<String,String> doRegister(RegisterInfo registerinfo){
		
		Map<String, String> result = new HashMap<String, String>();
		
		if (registerinfo == null) {
			logger.info("no register info accepted");
			result.put("status", "error");
			result.put("message", "registerInfoLost");
			return result;
		}
		
		String captcha = registerinfo.getCaptcha();
		String captchaLocal = (String)session.getAttribute("captcha");
		logger.info("captcha:" + captcha);
		logger.info("captchaLocal:" + captchaLocal);
		if( null == captcha || !captcha.equalsIgnoreCase(captchaLocal) ){
			//验证码错误
			result.put("status", "failed");
			result.put("message", "incorrectCaptcha");
			return result;
		}
		
		String phone = registerinfo.getPhoneToRegister();
		String email = registerinfo.getEmailToRegister();
		
		//如果验证码正确则开始生成静态随机盐和动态随机盐
		String staticSalt = random.nextBytes().toHex();
		String dynamicSalt = random.nextBytes().toHex();
		String password = registerinfo.getPasswordToRegister();
		
		HashRequest hashRequest = new HashRequest.Builder()
				.setSource(ByteSource.Util.bytes(password))	//对密码进行hash运算
				.setAlgorithmName("MD5")					//算法MD5
				.setSalt(new SimpleByteSource(staticSalt))	//加静态随机盐,默认是前缀加盐
				.setIterations(1)							//hash迭代次数1次
				.build();
		String passwordFinal = hashService.computeHash(hashRequest).toHex();
		
		logger.info("phone:" + phone);
		logger.info("email:" + email);
		logger.info("staticSalt:" + staticSalt);
		logger.info("dynamicSalt:" + dynamicSalt);
		logger.info("password:" + password);
		logger.info("passwordFinal:" + passwordFinal);
		
		Customer customer = null;
		try {
			customer = customerService.register(
					phone
					, email
					, passwordFinal
					, staticSalt
					, dynamicSalt
					);
		} catch (Exception e) {
			//保存实体异常
			result.put("status", "failed");
			result.put("message", "interruptedRegister");
			return result;
		}
		if(customer != null){
			//注册成功
			result.put("status", "succeed");
			result.put("staticSalt", staticSalt);
			result.put("dynamicSalt", dynamicSalt);
			//认证通过......
		}
		else {
			//
			result.put("status", "error");
			result.put("message", "unknownError");
		}
		return result;
	}

}
