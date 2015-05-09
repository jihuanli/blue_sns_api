package com.dabanniu.api.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dabanniu.core.constants.UserInteractionEnum;
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
	
	/**
	 * 最新列表
	 * @param factory_id 
	 * @param mark  标记id
	 * @param type 0-上拉刷新，1-下拉刷新
	 * @throws IOException
	 */
	@RequestMapping("/getNewMessagesByFactory.do")
	public void getNewMessagesByFactory(HttpServletRequest request,
			HttpServletResponse response, ApiContext apiContext, 
			@RequestParam(value="factory_id",required=true) long factory_id, 
			@RequestParam(value="mark",required=false,defaultValue="0") long mark,
			@RequestParam(value="type",required=false,defaultValue="0") int type,
			@RequestParam(value="page_cnt",required=false,defaultValue="2") int page_cnt) throws IOException {
		messageService.getNewMessagesByFactory(request, response, apiContext, factory_id, mark, type, page_cnt);
	}
	
	/**
	 * 最热列表
	 * @param factory_id 
	 * @throws IOException
	 */
	@RequestMapping("/getHotMessagesByFactory.do")
	public void getHotMessagesByFactory(HttpServletRequest request,
			HttpServletResponse response, ApiContext apiContext, 
			@RequestParam(value="factory_id",required=true) long factory_id) throws IOException {
		messageService.getHotMessagesByFactory(request, response, apiContext, factory_id);
	}
	
	@RequestMapping("/likeMessage.do")
	public void likeMessage(HttpServletRequest request,
			HttpServletResponse response, ApiContext apiContext, 
			@RequestParam(value="message_id",required=true) long message_id
			) throws IOException {
		messageService.userInteraction(request, response, apiContext, message_id, UserInteractionEnum.LIKE);
	}
	
	@RequestMapping("/unlikeMessage.do")
	public void unlikeMessage(HttpServletRequest request,
			HttpServletResponse response, ApiContext apiContext, 
			@RequestParam(value="message_id",required=true) long message_id
			) throws IOException {
		messageService.userInteraction(request, response, apiContext, message_id, UserInteractionEnum.UNLIKE);
	}
	
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
}
