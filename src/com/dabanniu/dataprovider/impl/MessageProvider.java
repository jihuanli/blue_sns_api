package com.dabanniu.dataprovider.impl;

import com.dabanniu.core.bean.dict.ListResultData;
import com.dabanniu.dao.impl.MessageDao;
import com.dabanniu.dataprovider.bean.MessageBean;

public class MessageProvider {
	
    private MessageDao messageDao;
    
    public ListResultData getNewMessagesByFactory(long factory_id,
    		long mark, int page_cnt, int type) {
    	ListResultData result = new ListResultData();
		if(mark < 0){
			return result;
		}
			
		result = messageDao.getNewMessagesByFactory(factory_id, mark, page_cnt, type);
		return result;
    }
    
    public ListResultData getHotMessagesByFactory(long factory_id) {
    	ListResultData result = new ListResultData();			
		result = messageDao.getHotMessagesByFactory(factory_id);
		return result;
    }
	
	public MessageBean getMessage(long messageId) throws Exception {
		return messageDao.getNews(messageId);
	}	

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
}
