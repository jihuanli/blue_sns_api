/*
 * $Id$
 *
 * Copyright (c) 2012 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.core.constants;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dabanniu.annotation.Message;

//--------------------- Change Logs----------------------
//<p>@author chenyijiu Initial Created at 2013-6-18<p>
//-------------------------------------------------------
public final class ApiErrorCode {

    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(ApiErrorCode.class);

    // 系统级别错误
    /**
     * 成功
     */
    @Message("操作成功")
    public static final int API_EC_SUCCESS = 0;

    /**
     * 未知异常
     */
    @Message("未知异常")
    public static final int API_EC_UNKNOWN = 1;

    /**
     * 请求方法不支持
     */
    @Message("请求方法不支持")
    public static final int API_EC_REST_METHOD_NOT_SUPPORT = 2;

    /**
     * Rest必须参数错误
     */
    @Message("Rest必须参数错误")
    public static final int API_EC_REST_PARAMETER_ERROR = 3;

    /**
     * session key无效或不存在
     */
    @Message("session key无效或不存在")
    public static final int API_EC_INVALID_SESSION_KEY = 4;

    /**
     * session key已失效
     */
    @Message("session key已失效")
    public static final int API_EC_SESSION_KEY_EXPIRED = 5;

    /**
     * 缺少通用参数
     */
    @Message("缺少通用参数")
    public static final int API_EC_LACKOF_COMMON_PARAMS = 6;

    /**
     * 用户名或密码错误
     */
    @Message("用户名或密码错误")
    public static final int API_EC_LOGIN_INFO_ERROR = 7;

    /**
     * 请求的资源未找到
     */
    @Message("请求的资源未找到")
    public static final int API_EC_RESOURCE_NOT_FOUND = 8;

    /**
     * 缺少必需的参数
     */
    @Message("缺少必需的参数")
    public static final int API_EC_INVALID_REQUIRED_PARAMETER = 9;

    /**
     * 无效的请求方式
     */
    @Message("无效的请求方式")
    public static final int API_EC_INVALID_REQUEST_TYPE = 10;

    /**
     * 上传的文件太大
     */
    @Message("上传的文件太大")
    public static final int API_EC_IMAGE_SIZE_TOO_LARGE = 11;

    /**
     * 创建日程天失败
     */
    @Message("创建日程天失败")
    public static final int API_EC_CREATE_DAY_FAIL = 12;

    /**
     * 
     */
    @Message("攻略天数不超过30天")
    public static final int API_EC_DAY_LIMIT = 13;

    /**
     * 每天元素不超过36个
     */
    @Message("每天元素不超过36个")
    public static final int API_EC_ELEMENT_LIMIT = 14;
    
    /**
     * 该页面已经创建过
     */
    @Message("该页面已经创建过")
    public static final int API_EC_SOURCE_URL_EXISTS = 15;

    private static final Map<Integer, String> MESSAGE_MAP = new HashMap<Integer, String>();

    /**
     * static initialize
     */
    static {
        staticInit();
    }

    public static final String asString(int resultCode) {
        return MESSAGE_MAP.get(resultCode);
    }

    /**
     * static init
     */
    private static void staticInit() {
        Field[] fields = ApiErrorCode.class.getFields();
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)//
                    && Modifier.isFinal(modifiers) && (field.getType() == int.class)) {
                try {
                    String message = field.getName();
                    if (field.isAnnotationPresent(Message.class)) {
                        message = field.getAnnotation(Message.class).value();
                    }
                    MESSAGE_MAP.put(field.getInt(null), message);
                } catch (IllegalArgumentException e) {
                    logger.error("staticInit()", e);
                } catch (IllegalAccessException e) {
                    logger.error("staticInit()", e);
                }
            }
        }
    }

    private ApiErrorCode() {
    }
}
