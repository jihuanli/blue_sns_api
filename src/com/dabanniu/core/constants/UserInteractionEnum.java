package com.dabanniu.core.constants;

public enum UserInteractionEnum {
	UNLIKE(0),
	LIKE(1);

	
	
	private UserInteractionEnum(int value){
		this.value=value;
	}
	
	private int value;
	
	public int value(){   
	   return value;  
	} 
	//测试枚举
	public static void main(String[] args) {
		System.out.println(UserInteractionEnum.LIKE.value());
	}
}
