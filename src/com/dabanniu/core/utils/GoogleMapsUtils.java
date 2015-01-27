package com.dabanniu.core.utils;

public class GoogleMapsUtils {
	private final static double EARTH_RADIUS = 6378.137;
	private static double rad(double d){  
		return d * Math.PI / 180.0;
	}
//	public static double GetDistance(double lat1, double lng1, double lat2, double lng2){  
//		double radLat1 = rad(lat1);   
//		double radLat2 = rad(lat2);  
//		double a = radLat1 - radLat2;  
//		double b = rad(lng1) - rad(lng2); 
//		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +     Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
//		s = s * EARTH_RADIUS;   
//		s = Math.round(s * 10000) / 10000;  
//		System.out.println(s);
//		return s;
//	}
	public static int GetDistance(double lat1, double lng1, double lat2, double lng2){  
		double radLat1 = rad(lat1);   
		double radLat2 = rad(lat2);  
		double a = radLat1 - radLat2;  
		double b = rad(lng1) - rad(lng2); 
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +     Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
		s = s * EARTH_RADIUS;   
		s = Math.round(s * 10000) / 10;  
		return new Double(s).intValue();
	}
	
	public static void main(String[] args) {
		System.out.println(GetDistance(39.9814d, 116.3132d, 43.7954, 87.6235));
	}
}
