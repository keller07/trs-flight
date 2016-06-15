package com.flight.trs.model.info;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月21日 下午3:51:49
 * @version V1.0
 */
public class LoginInfo {

	private String username;
	private String password;
	private String keepLoggedIn;
	private String captcha;
	
	public LoginInfo() {
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getKeepLoggedIn() {
		return keepLoggedIn;
	}
	public void setKeepLoggedIn(String keepLoggedIn) {
		this.keepLoggedIn = keepLoggedIn;
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
			.append("username=").append(getUsername())
			.append(", ")
			.append("password=").append(getPassword())
			.append(", ")
			.append("captcha=").append(getCaptcha())
			.append(", ")
			.append("keepLoggedIn=").append(getKeepLoggedIn())
			.append(" }");
		return sb.toString();
	}


}
