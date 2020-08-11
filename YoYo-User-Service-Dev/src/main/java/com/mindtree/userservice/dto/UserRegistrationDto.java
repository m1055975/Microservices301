package com.mindtree.userservice.dto;

import java.util.List;

public class UserRegistrationDto {

	private String message;

	private List<String> errorSignUp;

	private List<String> validInputDetails;
	
	public UserRegistrationDto() {
	}

	public UserRegistrationDto(String message, List<String> errorSignUp, List<String> validInputDetails) {
		super();
		this.message = message;
		this.errorSignUp = errorSignUp;
		this.validInputDetails = validInputDetails;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrorSignUp() {
		return errorSignUp;
	}

	public void setErrorSignUp(List<String> errorSignUp) {
		this.errorSignUp = errorSignUp;
	}

	public List<String> getValidInputDetails() {
		return validInputDetails;
	}

	public void setValidInputDetails(List<String> validInputDetails) {
		this.validInputDetails = validInputDetails;
	}

	@Override
	public String toString() {
		if(errorSignUp.size()==0) {
			return "UserRegistrationDto [message=" + message + ", validInputDetails="	+ validInputDetails + "]";
		}
		else {
			
		return "UserRegistrationDto [message=" + message + ", errorSignUp=" + errorSignUp + ", validInputDetails="
				+ validInputDetails + "]";
	}
	}
	
}
