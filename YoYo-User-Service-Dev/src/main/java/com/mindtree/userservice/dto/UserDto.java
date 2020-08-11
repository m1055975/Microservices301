package com.mindtree.userservice.dto;

public class UserDto {

	private String userId;

	private String userName;

	private String address;

	private String phoneNumber;

	private String emailId;

	private String password;

	public UserDto() {
	}

	public UserDto(String userId, String userName, String address, String phoneNumber, String emailId,
			String password) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userName=" + userName + ", address=" + address + ", phoneNumber="
				+ phoneNumber + ", emailId=" + emailId + ", password=" + password + "]";
	}

}
