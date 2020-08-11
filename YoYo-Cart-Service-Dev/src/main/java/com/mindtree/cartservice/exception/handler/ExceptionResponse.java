package com.mindtree.cartservice.exception.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExceptionResponse {

	private String message;
	
	private String path;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonIgnore
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public ExceptionResponse() {
		super();
		// constructor
	}

}
