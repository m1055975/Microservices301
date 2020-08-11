package com.mindtree.userservice.dto;

public class UpdateProfileDto {
	private String userName;

	private String address;

	private String phoneNumber;

	private String emailId;

	public UpdateProfileDto() {
	}

	public UpdateProfileDto(String userName, String address, String phoneNumber, String emailId) {
		super();
		this.userName = userName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "UpdateProfileDto [userName=" + userName + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ ", emailId=" + emailId + "]";
	}

}
