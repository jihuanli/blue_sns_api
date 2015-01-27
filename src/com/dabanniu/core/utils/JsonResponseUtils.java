package com.dabanniu.core.utils;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.CharEncoding;

import com.dabanniu.core.constants.ParamConstants;
import com.dabanniu.core.response.ApiResponse;
import com.dabanniu.core.response.ErrorResponse;

public class JsonResponseUtils {
	private JsonResponseUtils(){}

	public static final void writeJson(HttpServletResponse response,ApiResponse object) throws IOException {
		writeJson(response, JsonUtils.objectToJsonString(object));
	}
	public static final void writeJson(HttpServletResponse response,String json) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType(ParamConstants.JSON_PLAIN_CONTENT_TYPE);
		OutputStream os = response.getOutputStream();
		if(json==null){
			json = JsonUtils.objectToJsonString(new ErrorResponse("server is busy!"));
		}
		os.write(CompressUtils.getCompressBytes(json
				.getBytes(CharEncoding.UTF_8), 0));
		os.flush();
		os.close();
	}
}
