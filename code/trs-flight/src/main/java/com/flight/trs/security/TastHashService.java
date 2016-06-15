package com.flight.trs.security;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月3日 上午3:17:16
 * @version V1.0
 */
public class TastHashService {

	public static void main(String[] args) {
		DefaultHashService hashService = new DefaultHashService();
		hashService.setHashAlgorithmName("SHA-512");
		hashService.setPrivateSalt(new SimpleByteSource("123"));//添加私盐，默认无
		hashService.setGeneratePublicSalt(true);//添加公盐，默认false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
		hashService.setHashIterations(2);//hash迭代次数
		
		ByteSource source = ByteSource.Util.bytes("asd123");
		HashRequest hashRequest = new HashRequest.Builder()
				.setSource(source)
				.setAlgorithmName("MD5")
				.setSalt(new SimpleByteSource("123"))
				.setIterations(2)
				.build();
		String hex = hashService.computeHash(hashRequest).toHex();
		System.out.println(hashService.getHashAlgorithmName());
		System.out.println(hex);
		
	}

}
