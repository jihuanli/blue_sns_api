package com.dabanniu.dataprovider.impl;

import com.dabanniu.core.bean.dict.ListResultData;
import com.dabanniu.dao.impl.MessageDao;
import com.dabanniu.dataprovider.bean.MessageBean;

public class MessageProvider {
	
    private MessageDao messageDao;
    
    public ListResultData getMessagesByFactory(long factory_id,
    		long mark, int page_cnt, int order, int type) {
    	ListResultData result = new ListResultData();
		if(mark < 0){
			return result;
		}
			
		result = messageDao.getMessagesByFactory(factory_id, mark, page_cnt, order, type);
		return result;
    }
	
	public MessageBean getMessage(long messageId) throws Exception {
		return messageDao.getNews(messageId);
	}	

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
}
