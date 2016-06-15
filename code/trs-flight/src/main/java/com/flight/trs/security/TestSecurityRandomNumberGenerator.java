package com.flight.trs.security;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentRendererTokenTypes;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月3日 下午4:25:10
 * @version V1.0
 */
public class TestSecurityRandomNumberGenerator {

	public static void main(String[] args) {
		SecureRandomNumberGenerator random = new SecureRandomNumberGenerator();
		ByteSource bs = random.nextBytes();
		String source = "b81d9cedf903079f17c050c535e52f30";
		//b81d9cedf903079f17c050c535e52f30
		//staticSalt:8ea8eaa970691adf3d046a575e2b7e33
		
		//dynamicSalt:1232cebc03b6eae2d232d5a18955b303
		
		//finalPwd:2ccf86eef45b5d4c6149bf4cc7a19b80
		
		//computed:3de9f3e1c296790d7c8ad391228461f5
//		DefaultHashService hashService = new DefaultHashService();
//		
//		HashRequest hashRequest1 = new HashRequest.Builder()
//				.setSource(ByteSource.Util.bytes("asd123"))
//				.setAlgorithmName("MD5")
//				.setIterations(1)
//				.build();
//		String password1 = hashService.computeHash(hashRequest1).toHex();
//		System.out.println("未加盐：" + password1);
//		
//		HashRequest hashRequest2 = new HashRequest.Builder()
//				.setSource(ByteSource.Util.bytes("asd123"))
//				.setAlgorithmName("MD5")
//				.setSalt(new SimpleByteSource("b81d9cedf903079f17c050c535e52f30"))
//				//.setIterations(1)
//				.build();
//		String password2 = hashService.computeHash(hashRequest2).toHex();
//		System.out.println("加静态盐：" + password2);
//		
//		HashRequest hashRequest3 = new HashRequest.Builder()
//				.setSource(ByteSource.Util.bytes(password2))
//				.setAlgorithmName("MD5")
//				.setSalt(new SimpleByteSource("c32e8fde9048a49651fb6917d5de336e"))
//				//.setIterations(1)
//				.build();
//		String password3 = hashService.computeHash(hashRequest3).toHex();
//		System.out.println("加静态盐+加动态盐：" + password3);
	}

	//未加盐：bfd59291e825b5f2bbf1eb76569f8fe7
	//加静态盐：360afc08c803ee2bb876b4df8155f4c4
	//加静态盐+加动态盐：60dc6685bdae1fca6159ad1180452ff8
	
}
