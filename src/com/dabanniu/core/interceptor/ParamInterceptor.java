/*
 * $Id$
 *
 * Copyright (c) 2013 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dabanniu.core.constants.ParamConstants;
import com.dabanniu.core.parameter.ApiContext;
import com.dabanniu.core.utils.RestApiUtils;

//--------------------- Change Logs----------------------
// <p>@author chenyijiu Initial Created at 2013-6-19<p>
//-------------------------------------------------------
public class ParamInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
        	String sessionKey=request.getHeader(ParamConstants.SESSION_KEY);
        	if(sessionKey==null){
        		sessionKey=request.getParameter(ParamConstants.SESSION_KEY);
        	}
            ApiContext apiContext = new ApiContext(sessionKey,RestApiUtils.getUserAgent(request),
                    RestApiUtils.getRemoteAddr(request));
            request.setAttribute(ParamConstants.API_CONTEXT, apiContext);
            
            String version=request.getParameter(ParamConstants.VERSION);
            String device=request.getParameter(ParamConstants.DEVICE);
            String uDID=request.getParameter(ParamConstants.UDID);
            String appName=request.getParameter(ParamConstants.APPNAME);
            String channel=request.getParameter(ParamConstants.CHANNEL);
            
            request.setAttribute(ParamConstants.DEVICE, device);
            request.setAttribute(ParamConstants.VERSION, version);
            request.setAttribute(ParamConstants.UDID, uDID);
            request.setAttribute(ParamConstants.APPNAME, appName);
            request.setAttribute(ParamConstants.CHANNEL, channel);
        }
        return true;
    }

}
