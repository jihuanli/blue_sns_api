package com.dabanniu.utils;

import com.dabanniu.core.utils.CommonUtils;

public class AnonymousUtils {
	
	 private final static int[] avatarUrls = new int[] { 
			 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24
		 };
	 private final static String[] anoymousNick = new String[] { 
		 "兔女郎",
		 "红唇控",
		 "派对女王",
		 "女汉纸",
		 "白骨精",
		 "甜品控",
		 "棒棒糖",
		 "青柠檬",
		 "知蛛精",
		 "红太狼",
		 "猫女狼",
		 "冰激凌",
		 "伊丽莎白",
		 "赫本",
		 "凯瑟琳",
		 "波姬小丝",
		 "桑德拉"
	 };
	 public final static String getRandomUserAvatar() {
		 return String.format("http://dabanniu.oss.aliyuncs.com/anonymous/avatar/img_users_%s.png", avatarUrls[CommonUtils.random.nextInt(avatarUrls.length)]);
	 }
	 public final static String getRandomAnoymousNick() {
		 return String.format("假面%s", anoymousNick[CommonUtils.random.nextInt(anoymousNick.length)]);
	 }	 
}
