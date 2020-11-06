package com.upemor.petstorerest.exception;

import com.upemor.petstorerest.model.PetDTO;

public class PetErrorException extends PetDTO {
	
	private String errorMsg;
	
	public PetErrorException(final String errorMsg) {
	this.errorMsg = errorMsg;
	}
	public String getErrorMsg() {
	return errorMsg;
	
	}
}
