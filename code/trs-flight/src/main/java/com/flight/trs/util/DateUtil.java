package com.flight.trs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**   
 * 
 * @Description: TODO
 * @author le
 * @date 2016年4月21日 下午4:52:50
 * @version V1.0
 */
public class DateUtil {

	static final SimpleDateFormat sdfFromTimeStrToDate = new SimpleDateFormat("yyyyMMddHHmm");
	static final SimpleDateFormat sdfFromDateStrToDate = new SimpleDateFormat("yyyy-MM-dd");
	static final SimpleDateFormat sdfFromDateToString = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static void main(String[] args) {
		// TODO
		
		System.out.println(getDateTimeStrSimple("20160615","1318"));
		System.out.println(getDateTimeStrSimple("20160615","+1318"));
		System.out.println(getDateTimeStrSimple("20160615","#1318"));
		System.out.println(getDateTimeStrSimple("20160615","&1318"));
		System.out.println(getSimpleDateStr("2016-02-11"));
		
	}
	
	public static String getSimpleDateStr(String dateStr){
		return dateStr.substring(0, 4) + dateStr.substring(5, 7) + dateStr.substring(8);
	}
	
	public static Date getDate(String dateStr){
		Date targetDate = null;
		try {
			targetDate = sdfFromDateStrToDate.parse(dateStr);
		} catch (ParseException e) {
			// TODO
			//e.printStackTrace();
			return null;
		}
		return targetDate;
	}
	
	public static String getDateTimeStr(String date, String time) throws ParseException{
		String targetDateTime = "";
		String offset = null;
		if (time.length()==5) {
			targetDateTime = date + time.substring(1);
			offset = time.substring(0, 1);
		}
		else {
			targetDateTime = date + time;
		}
		Date targetDate = sdfFromTimeStrToDate.parse(targetDateTime);
		int dayOffset = 0;
		if ("+".equals(offset)) {
			dayOffset = 1;
		}else if ("#".equals(offset)) {
			dayOffset = 2;
		}else if ("&".equals(offset)) {
			dayOffset = 3;
		}
		targetDate = plusDay(targetDate,dayOffset);
		return sdfFromDateToString.format(targetDate);
	} 
	
	public static String getDateTimeStrSimple(String date, String time){
		
		String targetDateTimeStr = "";
		String offset = null;
		
		if (time.length()<=4) {
			targetDateTimeStr = date + time;
			return targetDateTimeStr;
		}
		
		targetDateTimeStr = date + time.substring(1);
		offset = time.substring(0, 1);
		
		try {
			Date targetDate = sdfFromTimeStrToDate.parse(targetDateTimeStr);
			int dayOffset = 0;
			if ("+".equals(offset)) {
				dayOffset = 1;
			}else if ("#".equals(offset)) {
				dayOffset = 2;
			}else if ("&".equals(offset)) {
				dayOffset = 3;
			}
			targetDate = plusDay(targetDate,dayOffset);
			return sdfFromTimeStrToDate.format(targetDate);
		} catch (ParseException e) {
			// TODO
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Date getDateTime(String date, String time) throws ParseException{
		String targetDateTime = "";
		String offset = null;
		if (time.length()==5) {
			targetDateTime = date + time.substring(1);
			offset = time.substring(0, 1);
		}
		else {
			targetDateTime = date + time;
		}
		Date targetDate = sdfFromTimeStrToDate.parse(targetDateTime);
		int dayOffset = 0;
		if ("+".equals(offset)) {
			dayOffset = 1;
		}else if ("#".equals(offset)) {
			dayOffset = 2;
		}else if ("&".equals(offset)) {
			dayOffset = 3;
		}
		targetDate = plusDay(targetDate,dayOffset);
		return targetDate;
	} 
	
	public static Date plusDay(Date targetDate, int dayOffset){
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(targetDate);
        rightNow.add(Calendar.DAY_OF_YEAR,dayOffset);//日期加n天
        return rightNow.getTime();
	} 
	
}
