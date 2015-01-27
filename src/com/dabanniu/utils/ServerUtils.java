package com.dabanniu.utils;


public class ServerUtils {
	
	private ServerUtils(){};
	
	private static boolean IS_TEST_SERVER=false;
	
	public static  boolean isTestServer(){
		return IS_TEST_SERVER;
	}
	static{
		String classPath = ServerUtils.class.getResource("/").getPath();
		System.out.println(classPath);
		if(classPath.indexOf("dbn_login_tomcat")!=-1){
			IS_TEST_SERVER=false;
		}else{
			IS_TEST_SERVER=true;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(ServerUtils.isTestServer());
	}
}
