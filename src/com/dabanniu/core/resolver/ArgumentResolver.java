/*
 * $Id$
 *
 * Copyright (c) 2013 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.core.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.dabanniu.core.constants.ParamConstants;
import com.dabanniu.core.parameter.ApiContext;

//--------------------- Change Logs----------------------
// <p>@author chenyijiu Initial Created at 2013-6-19<p>
//-------------------------------------------------------

public class ArgumentResolver implements HandlerMethodArgumentResolver {

    private static final ApiContext UNRESOLVED_API_CONTEXT = null;

    /* (non-Javadoc)
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#supportsParameter(org.springframework.core.MethodParameter)
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(ApiContext.class);
    }

    /* (non-Javadoc)
     * @see org.springframework.web.method.support.HandlerMethodArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.method.support.ModelAndViewContainer, org.springframework.web.context.request.NativeWebRequest, org.springframework.web.bind.support.WebDataBinderFactory)
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (methodParameter.getParameterType().equals(ApiContext.class)) {
            ApiContext apiContext = (ApiContext) webRequest.getAttribute(ParamConstants.API_CONTEXT,
                    RequestAttributes.SCOPE_REQUEST);
            if (apiContext == null) {
                return UNRESOLVED_API_CONTEXT;
            } else {
                return apiContext;
            }
        }else {
            return null;
        }
    }

}
