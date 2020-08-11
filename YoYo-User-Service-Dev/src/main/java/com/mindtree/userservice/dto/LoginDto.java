package com.mindtree.userservice.dto;

public class LoginDto {

	private String userId;
	private String password;


	public LoginDto() {
	}


	public LoginDto(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "LoginDto [userId=" + userId + ", password=" + password + "]";
	}
	
	
}
