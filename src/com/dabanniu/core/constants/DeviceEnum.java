package com.dabanniu.core.constants;

public enum DeviceEnum {
	iPhone("iPhone"),
	Android("Android");
	
	
	private DeviceEnum(String device){
		this.device=device;
	}
	
	private String device;
	
	@Override
	public String toString(){   
	   return device;  
	} 
	//测试枚举
	public static void main(String[] args) {
		System.out.println(DeviceEnum.iPhone.toString());
	}
}
