package com.flight.trs.model.info;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月21日 下午3:52:27
 * @version V1.0
 */
public class RegisterInfo {

	private String phoneToRegister;
	private String emailToRegister;
	private String passwordToRegister;
	private String captcha;

	public RegisterInfo() {
		// TODO 
	}
	
	public String getPhoneToRegister() {
		return phoneToRegister;
	}
	public void setPhoneToRegister(String phoneToRegister) {
		this.phoneToRegister = phoneToRegister;
	}
	public String getEmailToRegister() {
		return emailToRegister;
	}
	public void setEmailToRegister(String emailToRegister) {
		this.emailToRegister = emailToRegister;
	}
	public String getPasswordToRegister() {
		return passwordToRegister;
	}
	public void setPasswordToRegister(String passwordToRegister) {
		this.passwordToRegister = passwordToRegister;
	}
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName())
			.append("@")
			.append(Integer.toHexString(hashCode()))
			.append("{ ")
			.append("phoneToRegister=").append(getPhoneToRegister())
			.append(", ")
			.append("emailToRegister=").append(getEmailToRegister())
			.append(", ")
			.append("passwordToRegister=").append(getPasswordToRegister())
			.append(", ")
			.append("captcha=").append(getCaptcha())
			.append(" }");
		return sb.toString();
	}
	
}
