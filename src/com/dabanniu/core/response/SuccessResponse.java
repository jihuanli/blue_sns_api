package com.dabanniu.core.response;

public class SuccessResponse extends ApiResponse{
	public SuccessResponse(String content) {
		super.setErrorNum(0);
		super.setContent(content);
	}
}
