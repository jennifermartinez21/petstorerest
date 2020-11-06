package com.upemor.petstorerest.exception;

import com.upemor.petstorerest.model.CategoryDTO;

public class CategoryErrorException extends CategoryDTO {
	private String errorMsg;
	public CategoryErrorException(final String errorMsg) {
	this.errorMsg = errorMsg;
	}
	public String getErrorMsg() {
	return errorMsg;
	
	}
}
