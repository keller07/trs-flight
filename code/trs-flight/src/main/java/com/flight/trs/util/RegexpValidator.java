package com.flight.trs.util;

import java.util.regex.Pattern;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年1月21日 下午5:08:02
 * @version V1.0
 */
public class RegexpValidator{

	public static final String regexpPhone = "^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
	
	public static final String regexpPassword = "^[a-zA-Z0-9]{32,32}$";
	
	public static final String regexpEmail = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	
	public RegexpValidator() {
		// TODO 
	}
	
	public static boolean isPhone(String phone){
		return Pattern.matches(regexpPhone, phone);
	}
	
	public static boolean isEmail(String email){
		return Pattern.matches(regexpEmail, email);
	}
	
	public static boolean isPassword(String password) {
		return Pattern.matches(regexpPassword, password);
	}

}
