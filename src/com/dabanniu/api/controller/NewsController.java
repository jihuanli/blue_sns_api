package com.dabanniu.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dabanniu.core.parameter.ApiContext;
import com.dabanniu.service.impl.NewsService;

@Controller
public class NewsController {
	
	private NewsService newsService;
	
	/**
	 * 通过id获取新闻
	 * */
	@RequestMapping("/getNewsDetail.do")
    public void getNewsDetail(HttpServletRequest request,HttpServletResponse response,ApiContext apiContext,
    		@RequestParam(value="newsId",required=true) long newsId) throws Exception {
		newsService.getNewsDetail(request, response,apiContext,newsId);
    }	
	
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
}
