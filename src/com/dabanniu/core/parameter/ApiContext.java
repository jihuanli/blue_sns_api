/*
 * $Id$
 *
 * Copyright (c) 2013 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.core.parameter;

import org.apache.commons.lang3.StringUtils;

import com.dabanniu.core.bean.SessionKey;
import com.dabanniu.core.utils.SessionKeyUtils;

//--------------------- Change Logs----------------------
// <p>@author chenyijiu Initial Created at 2013-6-18<p>
//-------------------------------------------------------
public class ApiContext {

    //cookie user info
    private SessionKey sessionKey;
    
    private String sessionKeyStr;

    private String ua;

    private String ip;
    

    public ApiContext(String sessionKey, String ua, String ip) {
    	if(StringUtils.isNotBlank(sessionKey)){
    		this.sessionKeyStr=sessionKey;
    		this.sessionKey=SessionKeyUtils.decrypt(sessionKey);
    	}else{
    		this.sessionKeyStr="";
    	}
        this.ua = ua;
        this.ip = ip;
    }

	public Long getuID() {
		if (sessionKey==null){
			return null;
		}
		return this.sessionKey.getuID();
	}
	
	public Long getUserId() {
		if (sessionKey==null){
			return null;
		}
		return (long)this.sessionKey.getuID()/100;
	}

	public Long getCreationDate() {
		if (sessionKey==null){
			return null;
		}
		return this.sessionKey.getCreationDate();
	}

	public Integer getExpirationDate() {
		if (sessionKey==null){
			return null;
		}
		return this.sessionKey.getExpirationDate();
	}
	
	public boolean sessionKeyExpired(){
		if (sessionKey==null){
			return true;
		}
		return this.sessionKey.isExpired();
	}

	public String getUa() {
		return ua;
	}

	public String getIp() {
		return ip;
	}

	public String getSessionKeyStr() {
		return sessionKeyStr;
	}
	
}
