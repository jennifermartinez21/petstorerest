package com.upemor.petstorerest.exception;

import com.upemor.petstorerest.model.UserDTO;

public class UserErrorException extends UserDTO {
	private String errorMsg;
	public UserErrorException(final String errorMsg) {
	this.errorMsg = errorMsg;
	}
	public String getErrorMsg() {
	return errorMsg;
	
	}
}