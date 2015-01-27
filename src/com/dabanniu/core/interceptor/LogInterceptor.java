/*
 * $Id$
 *
 * Copyright (c) 2013 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.core.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dabanniu.core.constants.ParamConstants;
import com.dabanniu.core.parameter.ApiContext;
import com.dabanniu.core.utils.IpUtils;
import com.dabanniu.core.utils.JsonUtils;

//--------------------- Change Logs----------------------
// <p>@author chenyijiu Initial Created at 2013-6-19<p>
//-------------------------------------------------------
public class LogInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    private static final Logger accessLogger = LoggerFactory.getLogger("accessLogger");

    //paramMap|userId|ip|useragent
    //execTime|UDID|device|appName|version|userId|requesturi|method|sessionKey|useragent|paramMap|timeCost|ip
    private static final String LOG_FORMAT = "{}|{}|{}|{}|{}|{}|{}|{}|{}|{}|{}|{}|{}";

//    private ThreadLocal<StopWatch> stopWatchContent = new ThreadLocal<StopWatch>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            String requestUri = request.getRequestURI();

            if (logger.isDebugEnabled()) {
                logger.debug(String.format("requestUri is->%s", requestUri));
            }
            long now=System.currentTimeMillis();
            request.setAttribute("requestTime", now);
//            StopWatch stopWatch = new StopWatch(requestUri);
//            stopWatchContent.set(stopWatch);
//            stopWatch.start(requestUri);
        }
        return true;
    }

    @SuppressWarnings("unchecked")
	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        if (handler instanceof HandlerMethod) {
//            StopWatch stopWatch = stopWatchContent.get();
//            stopWatch.stop();
//            String requestUri = request.getRequestURI();
//            String method = request.getMethod();
//            long execTime = stopWatch.getTotalTimeMillis();
//            stopWatchContent.remove();
        	long execTime=System.currentTimeMillis();
        	String device= (String) request.getAttribute(ParamConstants.DEVICE);
            String version=(String) request.getAttribute(ParamConstants.VERSION);
            String uDID=(String)request.getAttribute(ParamConstants.UDID);
            String appName=(String)request.getAttribute(ParamConstants.APPNAME);
            
            String method=request.getMethod();
            String requestUri=request.getRequestURI();
            ApiContext apiContext=(ApiContext)request.getAttribute(ParamConstants.API_CONTEXT);
            String sessionKey=apiContext.getSessionKeyStr();
            Long userId=apiContext.getUserId();
        	String ua=apiContext.getUa();
        	Map paramMap=request.getParameterMap();
            //time|execTime|UDID|device|appName|version|userId|requesturi|method|sessionKey|useragent|paramMap|Timecost
        	Long requestTime=(Long)request.getAttribute("requestTime");
        	String ip=IpUtils.getRemoteAddr(request);
        	long now=System.currentTimeMillis();
        	long timeCost=-1;
        	if(requestTime!=null&&requestTime>0){
        		timeCost=now-requestTime;
        	}
            try {
            	HashMap map=new HashMap(paramMap);
            	map.remove("password");
            	map.remove(ParamConstants.DEVICE);
            	map.remove(ParamConstants.VERSION);
            	map.remove(ParamConstants.UDID);
            	map.remove(ParamConstants.APPNAME);
            	Set<String> keySet=map.keySet();
            	for (String key : keySet) {
					Object[] params=(Object[]) map.get(key);
					if(params!=null&&params.length==1){
						map.put(key, params[0]);
					}
				}
            	String paramStr=JsonUtils.objectToJsonString(map);
				accessLogger.info(LOG_FORMAT, new Object[] {execTime, uDID,device,appName ,version,userId,requestUri,method, sessionKey,ua,paramStr,timeCost,ip});
			} catch (Exception e) {
				accessLogger.info(LOG_FORMAT, new Object[] {execTime, uDID,device,appName ,version,userId,requestUri,method, sessionKey,ua,paramMap,timeCost,ip});
			}
        }
    }

}
