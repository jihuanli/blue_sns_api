package com.dabanniu.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.dabanniu.core.bean.dict.ListResultData;
import com.dabanniu.core.constants.UserInteractionEnum;
import com.dabanniu.dataprovider.bean.MessageBean;

public class MessageDao extends NamedParameterJdbcDaoSupport {
	
	private static final LightBeanPropertyRowMapper<MessageBean> MAPPER = new LightBeanPropertyRowMapper<MessageBean>(
			MessageBean.class);

	static {
		MAPPER.setPrimitivesDefaultedForNullValue(true);
	}
	
	private static final String GET_NEW_MESSAGE_LIST = "select * from message where factory_id = ? and message_id>? order by create_time desc limit 0,?";
	private static final String GET_OLD_MESSAGE_LIST = "select * from message where factory_id = ? and message_id<? order by create_time desc limit 0,?";
	public ListResultData getNewMessagesByFactory(long factory_id,
    		long mark, int page_cnt, int type) {
		ListResultData resultData = new ListResultData();
		
		List<MessageBean> list = null;
		if (type == 1) {
	    	list = this.getJdbcTemplate().query(GET_NEW_MESSAGE_LIST, new Object[] {factory_id, mark, page_cnt}, MAPPER);
	    } else {
	    	list = this.getJdbcTemplate().query(GET_OLD_MESSAGE_LIST, new Object[] {factory_id, mark, page_cnt}, MAPPER);
		}
		resultData.setData(list);
		resultData.setTotalNumber(9999);
		return resultData;
	}
	
	private static final String GET_HOT_MESSAGE_LIST = "select * from message where factory_id = ? order by like_cnt desc limit 20";
	public ListResultData getHotMessagesByFactory(long factory_id) {
		ListResultData resultData = new ListResultData();
		
		List<MessageBean> list = null;
	    list = this.getJdbcTemplate().query(GET_HOT_MESSAGE_LIST, new Object[] {factory_id}, MAPPER);
		resultData.setData(list);
		resultData.setTotalNumber(9999);
		return resultData;
	}

	/**
	 * 根据id获取消息
	 * */
	private static final String GET_MESSAGE_BY_ID = "select * from message where message_id=?  ";

	public MessageBean getNews(long newsId) {
		List<MessageBean> list = this.getJdbcTemplate().query(
				GET_MESSAGE_BY_ID,new Object[] { newsId }, MAPPER);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
	
	private static final String INSERT_USER_INTERACTION = "insert into user_interaction_log(content_type, content_id, action_type) values (0,?,?)";
	private static final String MODIFY_MESSAGE_USER_INTERACTION_NUM = "update message set like_cnt=like_cnt+? ,unlike_cnt=unlike_cnt+? where message_id=?";
	public boolean userInteraction(long message_id, UserInteractionEnum action) {
		boolean result = false;
		int like_increase = 0;
		int unlike_increase = 0;
		if (action.equals(UserInteractionEnum.LIKE)) {
			like_increase = 1;
		} else if (action.equals(UserInteractionEnum.UNLIKE)) {
			unlike_increase = 1;
		} else {
			return false;
		}
		result = this.getJdbcTemplate().update(INSERT_USER_INTERACTION,new Object[] { message_id, action.value()}) > 0;
		if (result) {
			result = this.getJdbcTemplate().update(MODIFY_MESSAGE_USER_INTERACTION_NUM,new Object[] { like_increase, unlike_increase, message_id}) > 0;
		}
		return result;
	}
}
