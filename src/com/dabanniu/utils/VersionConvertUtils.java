package com.dabanniu.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class VersionConvertUtils {
	private final static int defaultVersion=40000;
	public static int convertVersion(String version){
		int versionNum=defaultVersion;
		if(StringUtils.isNotBlank(version)){
			version=version.trim();
			if(NumberUtils.isDigits(version)){
				versionNum=1000000+Integer.parseInt(version);
			}else if(version.indexOf(".")!=-1){
				String[] versionPart=version.split("\\.");
				try {
					if(versionPart.length==3){
						versionNum=2000000+Integer.parseInt(versionPart[0])*10000+Integer.parseInt(versionPart[1])*100+Integer.parseInt(versionPart[2]);
					}else if(versionPart.length==2){
						versionNum=2000000+Integer.parseInt(versionPart[0])*10000+Integer.parseInt(versionPart[1])*100;
					}else{
						versionNum=2000000+Integer.parseInt(version);	
					}
				} catch (NumberFormatException e) {
					versionNum=-1;
				}
			}
		}
		return versionNum;
	}
}
