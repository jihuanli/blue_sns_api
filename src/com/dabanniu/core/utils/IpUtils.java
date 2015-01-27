/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.core.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

//--------------------- Change Logs----------------------
// <p>@author chenyijiu Initial Created at 2013-1-28<p>
//-------------------------------------------------------
public final class IpUtils {

    private IpUtils() {
    }

    private final static String IP_SEPARATOR = ",";

    /**
     * 获取客户端ip
     * 
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {

        String ip = request.getHeader("X-Real-IP");

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        } else {
            return ip;
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        } else {
            //当有多级反向代理时，x-forwarded-for值为多个时取最后一个ip地址
            int index = ip.lastIndexOf(IP_SEPARATOR);
            if (index > 0 && index < ip.length()) {
                ip = ip.substring(ip.lastIndexOf(IP_SEPARATOR) + 1, ip.length());
            }
            return ip;
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        } else {
            return ip;
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        } else {
            return ip;
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        } else {
            return ip;
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return StringUtils.defaultString(ip);
    }
}
