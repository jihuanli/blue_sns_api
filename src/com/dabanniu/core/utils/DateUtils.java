package com.dabanniu.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	private static final SimpleDateFormat IOS8601_FORMAT=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	
	private static final SimpleDateFormat YYYYMMDD_FORMAT=new SimpleDateFormat("yyyy-MM-dd");
	
	public static long getISO8601TimeStamp(String iso8601String){
		try {
			iso8601String = iso8601String.substring(0, 22) + iso8601String.substring(23);
			Date date = IOS8601_FORMAT.parse(iso8601String);
			return date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static long getYMDTimeStamp(String ymdString){
		try {
			Date date = YYYYMMDD_FORMAT.parse(ymdString);
			return date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
}
