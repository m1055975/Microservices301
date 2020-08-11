package com.mindtree.orderservice.dto;

public class EmailDto {
	private String userEmail;
	private String recipientEmail;
	private String userPassword;
	public EmailDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

}
