package com.flight.trs.util;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月6日 下午10:35:22
 * @version V1.0
 */
public class RandmSaltGenerator {

	private static SecureRandomNumberGenerator random = new SecureRandomNumberGenerator();

	public static String nextSalt(){
		return random.nextBytes().toHex();
	}
	
}
