package com.flight.trs.security.token;

import org.apache.shiro.authc.AuthenticationException;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月3日 上午2:02:52
 * @version V1.0
 */
public class IncorrectCaptchaException extends AuthenticationException{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -3621244029638103674L;

	public IncorrectCaptchaException() {
	}
	
	public IncorrectCaptchaException(String msg){
        super(msg);
    }

}
