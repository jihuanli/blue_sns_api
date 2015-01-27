package com.dabanniu.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.dabanniu.core.bean.SessionKey;
import com.dabanniu.core.constants.ConfigConstants;
import com.dabanniu.core.encrypt.OpenSSLDecryptor;

public class SessionKeyUtils {
	
	public static SessionKey decrypt(String sessionKeyStr){
		SessionKey sessionKey=null;
		try {
			if(StringUtils.isNotBlank(sessionKeyStr)){
				String info=new String(OpenSSLDecryptor.decrypt(null, OpenSSLDecryptor.parseHexStr2Byte(sessionKeyStr), ConfigConstants.SESSION_KEY_PW),"utf-8");
				if(StringUtils.isNotBlank(info)){
					String[] infos=info.split("@");
					if(NumberUtils.isNumber(infos[0])&&NumberUtils.isNumber(infos[1])&&NumberUtils.isNumber(infos[2])){
						sessionKey=new SessionKey();
						sessionKey.setuID(Long.parseLong(infos[0]));
						sessionKey.setCreationDate(Long.parseLong(infos[1])*1000);
						sessionKey.setExpirationDate(Integer.parseInt(infos[2]));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return sessionKey;
	}
	
	public static String encrypt(SessionKey sessionKey){	
		String encryptSessionKey=null;
		byte[] encrypted=null;
		try {
			encrypted = OpenSSLDecryptor.encrypt(null, sessionKey.getBytes(), ConfigConstants.SESSION_KEY_PW);
			encryptSessionKey=OpenSSLDecryptor.parseByte2HexStr(encrypted);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return encryptSessionKey;
	}
	public static void main(String[] args) {
		SessionKey sessionKey=new SessionKey();
		sessionKey.setuID(100302098l);
		sessionKey.setCreationDate(System.currentTimeMillis());
		sessionKey.setExpirationDate(30);
		System.out.println(SessionKeyUtils.encrypt(sessionKey));
	}
}
