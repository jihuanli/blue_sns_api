package com.dabanniu.dao.impl;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

public class MessageCommentDao extends NamedParameterJdbcDaoSupport {

	/**
	 * 根据id获取新闻
	 * */
	private static final String INSERT_MESSAGE_COMMENT = "insert into message_comment(message_id,content,create_time) values (?,?,?)";

	public boolean addMessageComment(long message_id, String content) {
		return this.getJdbcTemplate().update(INSERT_MESSAGE_COMMENT, new Object[] { message_id, content, System.currentTimeMillis()}) > 0;
	}	
}
