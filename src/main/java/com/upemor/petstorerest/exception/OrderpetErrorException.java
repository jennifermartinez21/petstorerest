package com.upemor.petstorerest.exception;

import com.upemor.petstorerest.model.Orderpet;

public class OrderpetErrorException extends Orderpet {
	
	private String errorMsg;
	
	public OrderpetErrorException(final String errorMsg) {
	this.errorMsg = errorMsg;
	}
	public String getErrorMsg() {
	return errorMsg;
	
	}
}
