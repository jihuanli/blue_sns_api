/*
 * $Id$
 *
 * Copyright (c) 2013 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.core.utils;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//--------------------- Change Logs----------------------
// <p>@author chenyijiu Initial Created at 2013-6-18<p>
//-------------------------------------------------------
public final class RestApiUtils {

    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(RestApiUtils.class);

    /**
     * for jsonp
     */
    private final static String DEFAULT_CALLBACK_PARAM = "callback";

    /**
     * for cross domain
     */
    private final static String DEFAULT_CROSS_DOMAIN_PARAM = "__cross_domain__";

    /**
     * get all request params
     * 
     * @param request
     * @return
     */
    public static final Map<String, String> getRequestParamMap(HttpServletRequest request) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("getRequestParamMap(HttpServletRequest) - [%s]", request.getRequestURI()));
        }
        Map<String, String> requestParamsMap = new HashMap<String, String>();

        @SuppressWarnings("unchecked")
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String param = names.nextElement();
            String value = request.getParameter(param);
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("getRequestParamMap(HttpServletRequest) - [%s=>%s]", param,
                        StringUtils.substring(value, 0, 500)));
            }
            requestParamsMap.put(param, StringUtils.substring(value, 0, 500));
        }
        return requestParamsMap;
    }

    /**
     * 获取cookie
     * 
     * @param request 当前request
     * @param name cookie名称
     * @return cookie值
     */
    public static final String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private static final void writeJson(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
//        request.setAttribute(ParamConstants.API_EXEC_CODE, jav.getErrorCode());
//        OutputStream os = response.getOutputStream();
//        String json = JsonUtils.objectToJsonString(jav);
//
//        //for cross domain
//        if (StringUtils.isNotBlank(request.getParameter(DEFAULT_CROSS_DOMAIN_PARAM))) {
//            response.setContentType(ParamConstants.TEXT_PLAIN_CONTENT_TYPE);
//            os.write(String.format("<script>window.name='%s';</script>",
//                    new String(Base64.encodeBase64(json.getBytes(CharEncoding.UTF_8)))).getBytes(CharEncoding.UTF_8));
//        } else {
//            response.setContentType(ParamConstants.JSON_PLAIN_CONTENT_TYPE);
//            if ("GET".equals(request.getMethod().toUpperCase())) {
//                String callback = request.getParameter(DEFAULT_CALLBACK_PARAM);
//                if (StringUtils.isBlank(callback)) {
//                    os.write(json.getBytes(CharEncoding.UTF_8));
//                } else {
//                    os.write(String.format("%s(%s);", callback, json).getBytes(CharEncoding.UTF_8));
//                }
//            } else {
//                os.write(json.getBytes(CharEncoding.UTF_8));
//            }
//        }
//
//        os.flush();
//        os.close();

    }

    public static final void writeJsonAndView(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
    	writeJson(request, response);
    }

    /**
     * 获取客户端ip
     * 
     * @param request
     * @return
     */
    public static final String getRemoteAddr(HttpServletRequest request) {
        //公司nginx会传递该值，所以优先取该值
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取客户端ua
     * 
     * @param request
     * @return
     */
    public static final String getUserAgent(HttpServletRequest request) {
        if (request != null && request.getHeader("User-Agent") != null) {
            return request.getHeader("User-Agent");
        }
        return "";
    }

    private RestApiUtils() {
    }

}
