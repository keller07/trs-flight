package com.flight.trs.util;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月22日 上午3:28:48
 * @version V1.0
 */
public class PaxUtil {

	public PaxUtil() {
		// TODO 
	}

	public static void main(String[] args) {
		// TODO

	}
	
	public static String getPaxTypeAlia(String paxType){
		if ("adult".equals(paxType)) {
			return "成人";
		}
		else if ("child".equals(paxType)) {
			return "儿童";
		}
		else{
			return "婴儿";
		}
	}
	
	public static String getPaxIdTypeAlia(String paxIdType){
		if ("ID card".equals(paxIdType)) {
			return "身份证";
		}
		else if ("passport".equals(paxIdType)) {
			return "护照";
		}
		else{
			return "其它";
		}
	}

}
