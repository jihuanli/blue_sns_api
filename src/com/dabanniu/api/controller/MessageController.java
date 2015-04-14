package com.dabanniu.api.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dabanniu.core.parameter.ApiContext;
import com.dabanniu.service.impl.MessageService;

@Controller
public class MessageController {
	
	private MessageService messageService;
	
	/**
	 * 通过id获取消息
	 * */
	@RequestMapping("/getMessageDetail.do")
    public void getNewsDetail(HttpServletRequest request,HttpServletResponse response,ApiContext apiContext,
    		@RequestParam(value="messageId",required=true) long messageId) throws Exception {
		messageService.getMessageDetail(request, response, apiContext, messageId);
	}
	
	@RequestMapping("/getMessagesByFactory.do")
	public void getMessagesByFactory(HttpServletRequest request,
			HttpServletResponse response, ApiContext apiContext, 
			@RequestParam(value="factory_id",required=true) long factory_id, 
			@RequestParam(value="mark",required=false,defaultValue="0") long mark,
			@RequestParam(value="order",required=false,defaultValue="1") int order,
			@RequestParam(value="type",required=false,defaultValue="0") int type) throws IOException {
		messageService.getMessagesByFactory(request, response, apiContext, factory_id, mark, order, type);
	}
	
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
}
