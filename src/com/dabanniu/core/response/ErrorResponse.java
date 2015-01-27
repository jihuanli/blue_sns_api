package com.dabanniu.core.response;

public class ErrorResponse extends ApiResponse {
	
	public ErrorResponse(String errorString) {
		super.setErrorNum(1);
		super.setErrorString(errorString);
	}
	
	public ErrorResponse(int errorNum,String errorString) {
		super.setErrorNum(errorNum);
		super.setErrorString(errorString);
	}
}
