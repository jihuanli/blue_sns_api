package com.dabanniu.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dabanniu.core.bean.dict.ListResultData;
import com.dabanniu.core.parameter.ApiContext;
import com.dabanniu.core.response.ErrorResponse;
import com.dabanniu.core.utils.JsonResponseUtils;
import com.dabanniu.core.utils.JsonUtils;
import com.dabanniu.dataprovider.bean.MessageBean;
import com.dabanniu.dataprovider.bean.MessagesResultBean;
import com.dabanniu.dataprovider.impl.MessageProvider;

public class MessageService {
	
    private MessageProvider messageProvider;
	
	public void getMessageDetail(HttpServletRequest request,
			HttpServletResponse response, ApiContext apiContext,long message_id) throws Exception{
		MessageBean message = messageProvider.getMessage(message_id);
		if (message == null) {
			JsonResponseUtils.writeJson(response, new ErrorResponse("无效的消息！"));
			return;
		}
		JsonResponseUtils.writeJson(response, JsonUtils.objectToJsonString(message));
		return;
	} 
    
	@SuppressWarnings("unchecked")
	public void getMessagesByFactory(HttpServletRequest request,
			HttpServletResponse response, ApiContext apiContext, 
			long factory_id, long mark, int order, int type, int page_cnt) throws IOException {
		ListResultData resultData = messageProvider.getMessagesByFactory(factory_id, mark, page_cnt, order, type);
		List<MessageBean> list = resultData.getData();
		MessagesResultBean output_messages = new MessagesResultBean();
		output_messages.setTotalNumber(resultData.getTotalNumber());
		output_messages.setMessages(list);
		
		if (list != null && list.size() > 0) {
			if(list.size()==0){
				output_messages.setMark("0");
			}else{
				if (type == 1) {// 下拉刷新，取最新的数据
				    output_messages.setMark(String.valueOf(list.get(list.size()-1).getMessage_id()));
				} else {
					output_messages.setMark(String.valueOf(list.get(0).getMessage_id()));
				}
			}
		}
		JsonResponseUtils.writeJson(response, JsonUtils.objectToJsonString(output_messages));
	}
	
	public void setMessageProvider(MessageProvider messageProvider) {
		this.messageProvider = messageProvider;
	}
}
