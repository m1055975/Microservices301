package com.mindtree.userservice.dto;

public class UserResponseDto {

	private String userId;

	private String userName;

	private String address;

	private String phoneNumber;

	private String emailId;

	private String password;

	private double yoyoBalance;

	public UserResponseDto() {
	}

	public UserResponseDto(String userId, String userName, String address, String phoneNumber, String emailId,
			String password, double yoyoBalance) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.password = password;
		this.yoyoBalance = yoyoBalance;
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

	
	public void setPassword(String password) {
		this.password = password;
	}

	public double getYoyoBalance() {
		return yoyoBalance;
	}

	public void setYoyoBalance(double yoyoBalance) {
		this.yoyoBalance = yoyoBalance;
	}

	@Override
	public String toString() {
		return "UserResponseDto [userId=" + userId + ", userName=" + userName + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", emailId=" + emailId + ", password=" + password + ", yoyoBalance="
				+ yoyoBalance + "]";
	}

}
