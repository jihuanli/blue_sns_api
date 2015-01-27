package com.dabanniu.utils;


public class SuggestionUtils {
//	紫外线指数 	等级 	
//  0-2   		 1   			最弱    			
//  3-4    	2     			弱      					
//  5-6    	3   			中等    						
//  7-9    	4    			 强      							
// >10   		 5   			很强    	
	public static Integer getUVSuggestion(String uvBrief){
		if("最弱".equals(uvBrief)){
			return 0;
		}else if("弱".equals(uvBrief)){
			return 3;
		}else if(uvBrief.indexOf("中")!=-1){
			return  5;
		}else if("强".equals(uvBrief)){
			return  7;
		}else if("很强".equals(uvBrief)){
			return 11;
		}else{
			System.err.println("找不到这个级别======>"+uvBrief);
			return null;
		}
	}
		
	public static String getUVBrief(int uvRank){
		if(uvRank==1){
			return "最弱";
		}else if(uvRank==2){
			return "弱";
		}else if(uvRank==3){
			return  "中等";
		}else if(uvRank==4){
			return  "强";
		}else if(uvRank>=5){
			return "很强";
		}else{
			System.err.println("找不到这个级别======>"+uvRank);
			return null;
		}
	}
	//pollution_l  pollution
	//优 			1
	//良 			2
	//轻度			3
	//中度			4
	public static Float getPm25Approximation(int PollutionRank){
		if(PollutionRank==1){
			return 36f;
		}else if(PollutionRank==2){
			return 73f;
		}else if(PollutionRank==3){
			return  124f;
		}else if(PollutionRank==4){
			return  169f;
		}else if(PollutionRank==5){
			return 243f;
		}else if(PollutionRank>5){
			return 393f;
		}else{
			System.err.println("找不到这个级别======>"+PollutionRank);
			return null;
		}
	}
}
