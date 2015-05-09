package com.dabanniu.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dabanniu.core.parameter.ApiContext;
import com.dabanniu.core.response.ErrorResponse;
import com.dabanniu.core.response.SuccessResponse;
import com.dabanniu.core.utils.JsonResponseUtils;
import com.dabanniu.dataprovider.impl.MessageCommentProvider;

public class MessageCommentService {
	
    private MessageCommentProvider messageCommentProvider;
	
	public void addMessageComment(HttpServletRequest request,
			HttpServletResponse response, ApiContext apiContext,
			long message_id, String content) throws Exception{
		boolean result = messageCommentProvider.addMessageComment(message_id, content);
		if (!result) {
			JsonResponseUtils.writeJson(response, new ErrorResponse("评论失败，请稍后重试！"));
			return;
		}
		JsonResponseUtils.writeJson(response, new SuccessResponse("评论成功！"));
		return;
	}    
	
	public void setMessageCommentProvider(MessageCommentProvider messageCommentProvider) {
		this.messageCommentProvider = messageCommentProvider;
	}
}
