package com.mindtree.authorization.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "userId")
	private String userId;

	@Column(name = "userName")
	private String userName;

	@Column(name = "userAddress")
	private String userAddress;

	@Column(name = "userPhoneNumber")
	private String userPhoneNumber;

	@Column(name = "userEmailId")
	private String userEmailId;

	@Column(name = "userPassword")
	private String userPassword;

	@Column(name = "yoyoBalance")
	private double yoyoBalance;

	@Column(name = "passwordBycrptor")
	private String passwordBycrptor;

	public User() {
		super();
	}

	public User(User user) {
		super();
		this.userId = user.getUserId();
		this.passwordBycrptor = user.getPasswordBycrptor();
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

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
}