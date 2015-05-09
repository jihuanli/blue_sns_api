package com.dabanniu.dataprovider.impl;

import com.dabanniu.dao.impl.MessageCommentDao;

public class MessageCommentProvider {
	
    private MessageCommentDao messageCommentDao;
	
	public boolean addMessageComment(long message_id, String content) throws Exception {
		return messageCommentDao.addMessageComment(message_id, content);
	}	
	
	
	public void setMessageCommentDao(MessageCommentDao messageCommentDao) {
		this.messageCommentDao = messageCommentDao;
	}
}
