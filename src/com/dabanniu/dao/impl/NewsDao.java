package com.dabanniu.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.dabanniu.model.News;

public class NewsDao extends NamedParameterJdbcDaoSupport {
	
	private static final LightBeanPropertyRowMapper<News> MAPPER = new LightBeanPropertyRowMapper<News>(
			News.class);

	static {
		MAPPER.setPrimitivesDefaultedForNullValue(true);
	}

	/**
	 * 根据id获取新闻
	 * */
	private static final String GET_NEWS_BY_ID = "select * from news where id=?  ";

	public News getNews(long productId) {
		List<News> list = this.getJdbcTemplate().query(
				GET_NEWS_BY_ID,new Object[] { productId }, MAPPER);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}	
}
