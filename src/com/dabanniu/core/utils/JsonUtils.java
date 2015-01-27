/*
 * $Id$
 *
 * Copyright (c) 2013 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.core.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


//--------------------- Change Logs----------------------
//<p>@author chenyijiu Initial Created at 2013-6-18<p>
//-------------------------------------------------------
@SuppressWarnings("unchecked")
public class JsonUtils {

    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper jsonObjectMapper = new ObjectMapper();// can reuse, share globally

    static {
    	jsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonObjectMapper.setDateFormat(df);
    }

  
    public static List getList(String jsonString, Class elementClass) throws JsonParseException, JsonMappingException, IOException {   
    	JavaType javaType = jsonObjectMapper.getTypeFactory().constructParametricType(ArrayList.class, elementClass);   
    	return jsonObjectMapper.readValue(jsonString, javaType);
    } 
    

    /**
     * 用jackson的安全方法转换，包括数组等
     * 
     * @param json
     * @param t
     * @return
     * @throws IOException
     */

    public static <T> T toTypeSafe(String json, TypeReference<T> t) throws IOException {
        if (json == null || json.length() == 0) {
            return (T) null;
        }
        return (T) jsonObjectMapper.readValue(getSafeJson(json), t);
    }

    /**
     * 用jackson的安全方法转换
     * 
     * @param <T>
     * @param json
     * @param t
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static <T> T toObjectSafe(String json, Class<T> t) throws IOException {
        if (json == null || json.length() == 0) {
            return (T) null;
        }
        return jsonObjectMapper.readValue(getSafeJson(json), t);
    }

    /**
     * 将json节点转换成对象
     * 
     * @param node
     * @param t
     * @return
     * @throws IOException
     */
    public static <T> T treeToValue(JsonNode node, Class<T> t) throws IOException {
        return jsonObjectMapper.treeToValue(node, t);
    }

    /**
     * 取安全的Json字符串
     * 
     * @param json
     * @return
     */
    public static String getSafeJson(String json) {
        if (json == null || json.length() == 0) {
            return json;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c < 32) {
                continue;
            }
            if (c == '\r' || c == '\n') {
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 将一个对象转成json字符串 <br/>
     * 字符串对象直接返回该值
     * 
     * @param o
     * @return
     */
    public static String objectToJsonString(Object o) {
        if (o == null) {
            return null;
        }
        //特殊处理
        if (o instanceof String) {
            return o.toString();
        }
        try {
            return jsonObjectMapper.writeValueAsString(o);
        } catch (Exception e) {
            logger.error("objectToJsonString", e);
        }
        return null;
    }

    private JsonUtils() {
    }
}
