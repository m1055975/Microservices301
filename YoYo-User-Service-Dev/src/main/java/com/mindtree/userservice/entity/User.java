package com.mindtree.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "userId")
	private String userId;

	@Column(name = "userName")
	private String userName;

	@Column(name = "userAddress")
	private String address;

	@Column(name = "userPhoneNumber")
	private String phoneNumber;

	@Column(name = "userEmailId")
	private String emailId;

	@Column(name = "userPassword")
	private String password;

	@Column(name = "yoyoBalance")
	private double yoyoBalance;

	@Column(name = "passwordBycrptor")
	private String passwordBycrptor;

	public User() {
	}

	public User(String userId, String userName, String address, String phoneNumber, String emailId, String password,
			double yoyoBalance, String passwordBycrptor) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.password = password;
		this.yoyoBalance = yoyoBalance;
		this.passwordBycrptor = passwordBycrptor;
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

	public double getYoyoBalance() {
		return yoyoBalance;
	}

	public void setYoyoBalance(double yoyoBalance) {
		this.yoyoBalance = yoyoBalance;
	}

	public String getPasswordBycrptor() {
		return passwordBycrptor;
	}

	public void setPasswordBycrptor(String passwordBycrptor) {
		this.passwordBycrptor = passwordBycrptor;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", Address=" + address + ", PhoneNumber="
				+ phoneNumber + ", EmailId=" + emailId + ", Password=" + password + ", yoyoBalance=" + yoyoBalance
				+ ", passwordBycrptor=" + passwordBycrptor + "]";
	}

}
