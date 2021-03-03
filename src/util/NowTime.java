package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NowTime {
	
	public static final long TENMINUTES = 1000 * 60 * 10;
	
	public static String getNowTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	public static String getOrderNumber() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	
	public static long getTime(String time) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime(); 
	}
	
}
