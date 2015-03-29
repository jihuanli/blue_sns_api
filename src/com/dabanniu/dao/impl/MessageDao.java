package com.dabanniu.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.dabanniu.model.Message;

public class MessageDao extends NamedParameterJdbcDaoSupport {
	
	private static final LightBeanPropertyRowMapper<Message> MAPPER = new LightBeanPropertyRowMapper<Message>(
			Message.class);

	static {
		MAPPER.setPrimitivesDefaultedForNullValue(true);
	}

	/**
	 * 根据id获取消息
	 * */
	private static final String GET_MESSAGE_BY_ID = "select * from message where message_id=?  ";

	public Message getNews(long newsId) {
		List<Message> list = this.getJdbcTemplate().query(
				GET_MESSAGE_BY_ID,new Object[] { newsId }, MAPPER);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}	
}
