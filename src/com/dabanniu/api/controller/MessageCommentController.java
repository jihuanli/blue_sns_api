package com.dabanniu.api.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dabanniu.core.constants.ContentType;
import com.dabanniu.core.constants.UserInteractionEnum;
import com.dabanniu.core.parameter.ApiContext;
import com.dabanniu.service.impl.MessageCommentService;
import com.dabanniu.service.impl.MessageService;

@Controller
public class MessageCommentController {
	
	private MessageCommentService messageCommentService;
	private MessageService messageService;
	
	/**
	 * 添加评论
	 * */
	@RequestMapping("/addMessageComment.do")
    public void getNewsDetail(HttpServletRequest request,HttpServletResponse response,ApiContext apiContext,
    		@RequestParam(value="message_id",required=true) long message_id,
    		@RequestParam(value="content",required=true) String content) throws Exception {
		messageCommentService.addMessageComment(request, response, apiContext, message_id, content);
	}
	
	@RequestMapping("/likeMessageComment.do")
	public void likeMessage(HttpServletRequest request,
			HttpServletResponse response, ApiContext apiContext, 
			@RequestParam(value="message_comment_id",required=true) long message_comment_id
			) throws IOException {
		messageService.userInteraction(request, response, apiContext, message_comment_id, UserInteractionEnum.LIKE, ContentType.MESSAGE_COMMENT);
	}
	
	@RequestMapping("/unlikeMessageComment.do")
	public void unlikeMessage(HttpServletRequest request,
			HttpServletResponse response, ApiContext apiContext, 
			@RequestParam(value="message_comment_id",required=true) long message_comment_id
			) throws IOException {
		messageService.userInteraction(request, response, apiContext, message_comment_id, UserInteractionEnum.UNLIKE, ContentType.MESSAGE_COMMENT);
	}
	
	public void setMessageCommentService(MessageCommentService messageCommentService) {
		this.messageCommentService = messageCommentService;
	}
	
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
}
