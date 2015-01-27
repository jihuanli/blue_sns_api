/*
 * $Id$
 *
 * Copyright (c) 2013 Qunar.com. All Rights Reserved.
 */
package com.dabanniu.core.resolver;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.dabanniu.core.response.ErrorResponse;
import com.dabanniu.core.utils.JsonResponseUtils;

//--------------------- Change Logs----------------------
// <p>@author chenyijiu Initial Created at 2013-6-19<p>
//-------------------------------------------------------
public class RestExceptionResolver extends AbstractHandlerExceptionResolver {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(RestExceptionResolver.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver
	 * #doResolveException(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object,
	 * java.lang.Exception)
	 */
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		// 用户请求取消或断开导致io错误
		if (response.isCommitted()) {
			logger
					.error(
							"RestExceptionResolver Exception After Committed(maybe client disconnected):",
							ex);
			return null;
		}
		if (ex instanceof MissingServletRequestParameterException
				|| ex instanceof HttpRequestMethodNotSupportedException) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			try {
				JsonResponseUtils.writeJson(response, new ErrorResponse(
						"无效的请求-参数无效"));
			} catch (IOException e) {
				System.out.println("无效的请求:" + request.getRequestURI());
			}
		} else {
			try {
				JsonResponseUtils.writeJson(response, new ErrorResponse("server is busy!")); 
			}catch (IOException e) {
				System.out.println("无效的请求:" + request.getRequestURI());
			}
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			logger.error("RestExceptionResolver Exception:", ex);
		}
		return null;
		// if (StringUtils.startsWithIgnoreCase(request.getRequestURI(),
		// "/api/")) {
		// if (ex instanceof MissingServletRequestParameterException) {
		// return null;
		// } else if (ex instanceof HttpRequestMethodNotSupportedException) {
		// return null;
		// } else if (ex instanceof MaxUploadSizeExceededException) {
		// return null;
		// } else {
		// logger.error("RestExceptionResolver Exception:", ex);
		// return null;
		// }
		// } else {
		// if (ex instanceof MissingServletRequestParameterException
		// || ex instanceof HttpRequestMethodNotSupportedException) {
		// response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		// try {
		// JsonResponseUtils.writeJson(response, new
		// ErrorResponse("无效的请求-参数无效"));
		// } catch (IOException e) {
		// System.out.println("无效的请求:"+request.getRequestURI());
		// }
		// return null;
		// } else {
		// response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		// logger.error("RestExceptionResolver Exception:", ex);
		// }
		// return null;
		// }

	}

}
