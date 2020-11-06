package com.upemor.petstorerest.exception;

import com.upemor.petstorerest.model.TagDTO;

public class TagErrorException extends TagDTO {
	
	private String errorMsg;
	
	public TagErrorException(final String errorMsg) {
	this.errorMsg = errorMsg;
	}
	public String getErrorMsg() {
	return errorMsg;
	
	}

}
