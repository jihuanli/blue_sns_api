package com.dabanniu.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.dabanniu.core.bean.dict.ListResultData;
import com.dabanniu.dataprovider.bean.MessageBean;

public class MessageDao extends NamedParameterJdbcDaoSupport {
	
	private static final LightBeanPropertyRowMapper<MessageBean> MAPPER = new LightBeanPropertyRowMapper<MessageBean>(
			MessageBean.class);

	static {
		MAPPER.setPrimitivesDefaultedForNullValue(true);
	}
	
	private static final String GET_NEW_MESSAGE_LIST = "select * from message where factory_id = ? and message_id>? order by create_time desc limit 0,?";
	private static final String GET_OLD_MESSAGE_LIST = "select * from message where factory_id = ? and message_id<? order by create_time desc limit 0,?";
	public ListResultData getMessagesByFactory(long factory_id,
    		long mark, int page_cnt, int order, int type) {
		ListResultData resultData = new ListResultData();
		
		List<MessageBean> list = null;
		if (type == 0) {
		    list = this.getJdbcTemplate().query(GET_NEW_MESSAGE_LIST, new Object[] {factory_id, mark, page_cnt}, MAPPER);
		} else {
			list = this.getJdbcTemplate().query(GET_OLD_MESSAGE_LIST, new Object[] {factory_id, mark, page_cnt}, MAPPER);
		}
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
}
