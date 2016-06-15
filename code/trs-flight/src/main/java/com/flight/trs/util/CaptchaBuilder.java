package com.flight.trs.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月3日 下午5:58:24
 * @version V1.0
 */
public class CaptchaBuilder {
	
	private static final Random random = new Random();

	public static Color getRandColor(int fc,int bc)     
    {     
        if(fc>255) fc=255;     
        if(bc>255) bc=255;     
        int r=fc+random.nextInt(bc-fc);     
        int g=fc+random.nextInt(bc-fc);     
        int b=fc+random.nextInt(bc-fc);     
        return new Color(r,g,b);     
    }  
	
	public static BufferedImage getCaptchaImage(int width, int height, String captcha){
		//设置字母的大小,大小     
	    Font mFont = new Font("Tekton Pro", Font.PLAIN, 32);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);     
        Graphics g = image.getGraphics();     
        g.setColor(getRandColor(200,250));     
        g.fillRect(1, 1, width-1, height-1);     
        g.setColor(new Color(102,102,102));     
        g.drawRect(0, 0, width-1, height-1);     
        g.setFont(mFont);     
        g.setColor(getRandColor(160,200));     
        //画随机线     
        for (int i=0;i<155;i++)     
        {     
            int x = random.nextInt(width - 1);     
            int y = random.nextInt(height - 1);     
            int xl = random.nextInt(6) + 1;     
            int yl = random.nextInt(12) + 1;     
            g.drawLine(x,y,x + xl,y + yl);     
        }     
        //从另一方向画随机线     
        for (int i = 0;i < 70;i++)     
        {     
            int x = random.nextInt(width - 1);     
            int y = random.nextInt(height - 1);     
            int xl = random.nextInt(12) + 1;     
            int yl = random.nextInt(6) + 1;     
            g.drawLine(x,y,x - xl,y - yl);     
        }     
        //画随机验证码
        char[] captchaChars = captcha.toCharArray();
        for (int i=0;i<captchaChars.length;i++)     
        {     
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));     
            g.drawString(String.valueOf(captchaChars[i]),((width-16)/captchaChars.length)*i+10,height-8);     
        }     
        g.dispose(); 
        return image;
	}

	//产生[0-9A-Z]指定位数的随机验证码
	public static String getCaptcha(int length){
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
	
}
