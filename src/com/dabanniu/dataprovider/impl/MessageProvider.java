package com.dabanniu.dataprovider.impl;

import com.dabanniu.core.utils.BeanUtils;
import com.dabanniu.dao.impl.MessageDao;
import com.dabanniu.dataprovider.bean.MessageBean;
import com.dabanniu.model.Message;

public class MessageProvider {
	
    private MessageDao messageDao;
	
	public MessageBean getMessage(long messageId) throws Exception {
		Message message = messageDao.getNews(messageId);
		if (message == null) {
			return null;
		}
		MessageBean bean = new MessageBean(); 
		BeanUtils.copyProperties(message, bean);
		return bean;
	}	

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
}
