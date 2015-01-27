package com.dabanniu.core.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ApiResponse {
	
	@JsonProperty("error")
	private int errorNum=1;
	
	private String content;
	
	private String errorString;
	
	public int getErrorNum() {
		return errorNum;
	}
	public void setErrorNum(int errorNum) {
		this.errorNum = errorNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getErrorString() {
		return errorString;
	}
	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}
	
}
