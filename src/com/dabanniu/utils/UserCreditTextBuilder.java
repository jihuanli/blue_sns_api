package com.dabanniu.utils;


public class UserCreditTextBuilder {
	public static String buildCommonTip(String tipContent,int credit,int experience){
		StringBuffer content=new StringBuffer(tipContent);
		if(credit>0){
			content.append(",积分+"+credit);
		}
		if(experience>0){
			content.append(",经验+"+experience);
		}
		return content.toString();
	}
}
