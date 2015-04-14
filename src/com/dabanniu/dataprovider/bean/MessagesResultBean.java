package com.dabanniu.dataprovider.bean;

import java.util.ArrayList;
import java.util.List;

public class MessagesResultBean {
	
	private int error=0;
		
	private int totalNumber=0;
	
	private String mark="0";

	private List<MessageBean> messages = new ArrayList<MessageBean>();

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public List<MessageBean> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageBean> messages) {
		this.messages = messages;
	}
}
