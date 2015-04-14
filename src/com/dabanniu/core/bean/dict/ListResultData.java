package com.dabanniu.core.bean.dict;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unchecked")
public class ListResultData {
	
	private int totalNumber = 0;
	
	private List data=new ArrayList();
	
	private String pageMark = "0";
	
	private Integer hasMore;
	
	private Long cacheMark;
	
	private int topicNumber = 0;
	
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
	public void setHasMore(Integer hasMore) {
		this.hasMore = hasMore;
	}
	public Integer getHasMore() {
		return hasMore;
	}
	public Long getCacheMark() {
		return cacheMark;
	}
	public void setCacheMark(Long cacheMark) {
		this.cacheMark = cacheMark;
	}
	public String getPageMark() {
		return pageMark;
	}
	public void setPageMark(String pageMark) {
		this.pageMark = pageMark;
	}
	public int getTopicNumber() {
		return topicNumber;
	}
	public void setTopicNumber(int topicNumber) {
		this.topicNumber = topicNumber;
	}
	
	
	
}
