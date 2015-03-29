package com.dabanniu.dataprovider.impl;

import com.dabanniu.core.utils.BeanUtils;
import com.dabanniu.dao.impl.NewsDao;
import com.dabanniu.dataprovider.bean.NewsBean;
import com.dabanniu.model.News;

public class NewsProvider {
	
    private NewsDao newsDao;
	
	/**
	 * 获取产品信息
	 * @throws Exception 
	 * */
	
	public NewsBean getNews(long newsId) throws Exception {
		News news = newsDao.getNews(newsId);
		if (news == null) {
			return null;
		}
		NewsBean bean = new NewsBean(); 
		BeanUtils.copyProperties(news, bean);
		return bean;
	}	
	
	
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
}
