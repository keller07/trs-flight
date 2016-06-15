package com.flight.trs.util;

import java.text.DecimalFormat;
//import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月22日 上午9:37:23
 * @version V1.0
 */
public class OrderNoGenerator {

	private static long counter = 0;
    
    public synchronized static String getObject() throws Exception {
        String date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        //String sequ = new DecimalFormat("0000").format((counter ++)%10000);
        return date ;//+ sequ
    }
 
    public synchronized static String getNo() {
    	String date = new SimpleDateFormat("MMddHH").format(new Date());
        String sequ = new DecimalFormat("0000").format(counter ++);
        return date + sequ;
	}
    
    public Class<String> getObjectType() {
        return String.class;
    }
 
    public boolean isSingleton() {
        return false;
    }
 
    public static void reset() {
    	OrderNoGenerator.counter = 0;
    }

	public static void main(String[] args) {
		// TODO
		try {
			System.out.println(OrderNoGenerator.getObject());
			System.out.println(OrderNoGenerator.getObject());
			System.out.println(OrderNoGenerator.getObject());
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
	}

}
