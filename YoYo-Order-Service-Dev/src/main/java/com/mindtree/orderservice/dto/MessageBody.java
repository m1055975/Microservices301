package com.mindtree.orderservice.dto;

public class MessageBody {
	private String redeemCode;

	private String endpoint;

	public MessageBody(String redeemCode, String endpoint) {
		super();
		this.redeemCode = redeemCode;
		this.endpoint = endpoint;
	}

	public String getRedeemCode() {
		return redeemCode;
	}

	public void setRedeemCode(String redeemCode) {
		this.redeemCode = redeemCode;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	@Override
	public String toString() {
		return "You have recieved a few gifts, kindly redeem it! \nYour redeem Code is : " + redeemCode
				+ "\nThe Link is : " + endpoint;
	}
}
