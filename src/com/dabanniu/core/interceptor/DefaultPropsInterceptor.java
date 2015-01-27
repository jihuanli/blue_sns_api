/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.core.interceptor;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dabanniu.core.constants.ParamConstants;

/**
 * 默认属性
 */
//--------------------- Change Logs----------------------
// <p>@author chenyijiu Initial Created at 2013-6-19<p>
//-------------------------------------------------------
public class DefaultPropsInterceptor extends HandlerInterceptorAdapter implements InitializingBean, ResourceLoaderAware {

    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(DefaultPropsInterceptor.class);

    private Map<String, Object> defaultProps = new HashMap<String, Object>();

    private String defaultPropsFile;

    private ResourceLoader resourceLoader;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //其它配置
        if (!defaultProps.isEmpty()) {
            for (Entry<String, Object> entry : defaultProps.entrySet()) {
                request.setAttribute(entry.getKey(), entry.getValue());
            }
        }

        return true;
    }

    /* (non-Javadoc)
     * @see org.springframework.context.ResourceLoaderAware#setResourceLoader(org.springframework.core.io.ResourceLoader)
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(defaultPropsFile, "defaultPropsFile can not be null");
        loadDefaultPropsFile();
    }

    /**
     * @param defaultPropsFile the defaultPropsFile to set
     */
    public void setDefaultPropsFile(String defaultPropsFile) {
        this.defaultPropsFile = defaultPropsFile;
    }

    private void loadDefaultPropsFile() {
        //default add host-name
        try {
            InetAddress addr = InetAddress.getLocalHost();
            if (addr != null) {
                defaultProps.put(ParamConstants.HOST_NAME, addr.getHostName());
            }
        } catch (Exception e) {
            logger.error("get local host name error", e);
        }

        Properties prop = new Properties();
        try {
            Resource fileResource = resourceLoader.getResource(defaultPropsFile);
            if (fileResource != null) {
                prop.load(fileResource.getInputStream());
            } else {
                logger.error("can not found default props file :" + defaultPropsFile);
            }
        } catch (IOException e) {
            logger.error("load properties file fail", e);
            return;
        }
        if (prop.isEmpty()) {
            return;
        }
        for (Entry<Object, Object> entry : prop.entrySet()) {
            defaultProps.put(entry.getKey().toString(), entry.getValue());
        }
    }
}
