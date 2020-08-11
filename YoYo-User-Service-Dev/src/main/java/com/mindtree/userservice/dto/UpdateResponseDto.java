package com.mindtree.userservice.dto;

import java.util.List;

public class UpdateResponseDto {

	
	List<String> updatedResponse;
	
	List<String> errorsInUpdate;

	
	public List<String> getErrorsInUpdate() {
		return errorsInUpdate;
	}

	public void setErrorsInUpdate(List<String> errorsInUpdate) {
		this.errorsInUpdate = errorsInUpdate;
	}

	public List<String> getUpdatedResponse() {
		return updatedResponse;
	}

	public void setUpdatedResponse(List<String> updatedResponse) {
		this.updatedResponse = updatedResponse;
	}

	@Override
	public String toString() {
		return "UpdateResponseDto [updatedResponse=" + updatedResponse + ", errorsInUpdate=" + errorsInUpdate + "]";
	}
	
	
	
}
