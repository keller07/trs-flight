package com.flight.trs.util;

import java.util.Random;

import javax.swing.plaf.synth.SynthSeparatorUI;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月22日 上午10:09:31
 * @version V1.0
 */
public class PNRGenerator {

	private static final Random random = new Random();

	public static String getPNR(){
		return getRandom(6);
	}
	
	//产生[0-9A-Z]指定位数的随机码
	public static String getRandom(int length){
        StringBuilder sBcaptcha=new StringBuilder();
        for (int i=0;i<length;i++)     
        {     
            int rtmp = random.nextInt(36) ;  
            //如果是0-9则加上48转化为数字的ASCII，如果是10-35则需先加上55转化为字母的ASCII，再转化为字符
            char ctmp = rtmp < 10 ? (char)(rtmp+48): (char)(rtmp+55); 
            sBcaptcha.append(ctmp);         
        }     
        return sBcaptcha.toString();
	} 
	
	public static void main(String[] args) {
		// TODO
		System.out.println(getPNR());
		System.out.println(getPNR());
		System.out.println(getPNR());
	}

}
