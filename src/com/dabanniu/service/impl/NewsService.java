package com.dabanniu.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dabanniu.core.parameter.ApiContext;
import com.dabanniu.core.response.ErrorResponse;
import com.dabanniu.core.utils.JsonResponseUtils;
import com.dabanniu.core.utils.JsonUtils;
import com.dabanniu.dataprovider.bean.NewsBean;
import com.dabanniu.dataprovider.impl.NewsProvider;

public class NewsService {
	
    private NewsProvider newsProvider;
	
	public void getNewsDetail(HttpServletRequest request,
			HttpServletResponse response, ApiContext apiContext,long newsId) throws Exception{
		NewsBean news = newsProvider.getNews(newsId);
		if (news == null) {
			JsonResponseUtils.writeJson(response, new ErrorResponse("无效的产品！"));
			return;
		}
		JsonResponseUtils.writeJson(response, JsonUtils.objectToJsonString(news));
		return;
	}    
    
	public void setNewsProvider(NewsProvider newsProvider) {
		this.newsProvider = newsProvider;
	}
}
