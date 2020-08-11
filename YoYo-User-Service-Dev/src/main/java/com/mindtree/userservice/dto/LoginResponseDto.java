package com.mindtree.userservice.dto;

public class LoginResponseDto extends ResponseMessage{

	private String userId;
	private String userName;
	private String yoyoBalance;

	public LoginResponseDto() {
	}

	public LoginResponseDto(String userId, String userName, String yoyoBalance) {
		super();
		this.userId = userId;
		this.userName = userName;
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

	public String getYoyoBalance() {
		return yoyoBalance;
	}

	public void setYoyoBalance(String yoyoBalance) {
		this.yoyoBalance = yoyoBalance;
	}

	@Override
	public String toString() {
		return "LoginResponseDto [userId=" + userId + ", userName=" + userName + ", yoyoBalance=" + yoyoBalance + "]";
	}

}
