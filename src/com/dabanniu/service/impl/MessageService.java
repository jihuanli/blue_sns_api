package com.dabanniu.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dabanniu.core.parameter.ApiContext;
import com.dabanniu.core.response.ErrorResponse;
import com.dabanniu.core.utils.JsonResponseUtils;
import com.dabanniu.core.utils.JsonUtils;
import com.dabanniu.dataprovider.bean.MessageBean;
import com.dabanniu.dataprovider.impl.MessageProvider;

public class MessageService {
	
    private MessageProvider messageProvider;
	
	public void getMessageDetail(HttpServletRequest request,
			HttpServletResponse response, ApiContext apiContext,long messageId) throws Exception{
		MessageBean message = messageProvider.getMessage(messageId);
		if (message == null) {
			JsonResponseUtils.writeJson(response, new ErrorResponse("无效的消息！"));
			return;
		}
		JsonResponseUtils.writeJson(response, JsonUtils.objectToJsonString(message));
		return;
	}    
    
	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}
}
