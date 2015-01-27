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

import com.dabanniu.annotation.SessionKeyRequired;
import com.dabanniu.core.constants.ParamConstants;
import com.dabanniu.core.parameter.ApiContext;
import com.dabanniu.core.response.ErrorResponse;
import com.dabanniu.core.utils.JsonResponseUtils;

//--------------------- Change Logs----------------------
// <p>@author chenyijiu Initial Created at 2013-1-27<p>
//-------------------------------------------------------
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            ApiContext apiContext = (ApiContext) request.getAttribute(ParamConstants.API_CONTEXT);

            HandlerMethod handlerMethod = (HandlerMethod) handler;

            SessionKeyRequired anno = null;
            if (handlerMethod.getMethod().isAnnotationPresent(SessionKeyRequired.class)) {
                anno = handlerMethod.getMethod().getAnnotation(SessionKeyRequired.class);
            }
            if (anno != null&&anno.isRequired()&&apiContext.sessionKeyExpired()) {
            	JsonResponseUtils.writeJson(response, new ErrorResponse(9999,"登录信息已过期，请重新登录"));
            	return false;
            }
        }
        return true;
    }
}
