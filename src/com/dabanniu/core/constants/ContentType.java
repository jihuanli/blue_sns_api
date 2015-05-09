package com.dabanniu.core.constants;

public enum ContentType {
	MESSAGE(0),
	MESSAGE_COMMENT(1);

	
	
	private ContentType(int value){
		this.value=value;
	}
	
	private int value;
	
	public int value(){   
	   return value;  
	} 
	//测试枚举
	public static void main(String[] args) {
		System.out.println(ContentType.MESSAGE.value());
	}
}
