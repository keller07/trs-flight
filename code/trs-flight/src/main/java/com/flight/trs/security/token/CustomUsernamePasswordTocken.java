package com.flight.trs.security.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月3日 上午1:26:46
 * @version V1.0
 */
public class CustomUsernamePasswordTocken extends UsernamePasswordToken {

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 4038099113409063740L;

	//用于存储用户输入的验证码
	private String captcha;
	
	public CustomUsernamePasswordTocken() {
		// TODO 
	}

	public CustomUsernamePasswordTocken(String username, char[] password, String captcha) {
		super(username, password);
		this.captcha = captcha;
	}

	public CustomUsernamePasswordTocken(String username, String password, String captcha) {
		super(username, password);
		this.captcha = captcha;
	}

	public CustomUsernamePasswordTocken(String username, char[] password, String host, String captcha) {
		super(username, password, host);
		this.captcha = captcha;
	}

	public CustomUsernamePasswordTocken(String username, String password, String host, String captcha) {
		super(username, password, host);
		this.captcha = captcha;
	}

	public CustomUsernamePasswordTocken(String username, char[] password, boolean rememberMe, String captcha) {
		super(username, password, rememberMe);
		this.captcha = captcha;
	}

	public CustomUsernamePasswordTocken(String username, String password, boolean rememberMe, String captcha) {
		super(username, password, rememberMe);
		this.captcha = captcha;
	}

	public CustomUsernamePasswordTocken(String username, char[] password, boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public CustomUsernamePasswordTocken(String username, String password, boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}
