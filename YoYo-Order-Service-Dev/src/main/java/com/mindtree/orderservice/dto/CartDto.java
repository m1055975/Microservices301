package com.mindtree.orderservice.dto;

import java.util.List;

public class CartDto {
	private int cartId;
	private String userId;
	private String redeemCode;
	private double totalAmount;
	private boolean paymentStatus;
	private String userEmail;
	private String recipientEmail;
	private String userPassword;
	private List<ProductDto> allSelectedProduct;

	public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRedeemCode() {
		return redeemCode;
	}

	public void setRedeemCode(String redeemCode) {
		this.redeemCode = redeemCode;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public List<ProductDto> getAllSelectedProduct() {
		return allSelectedProduct;
	}

	public void setAllSelectedProduct(List<ProductDto> allSelectedProduct) {
		this.allSelectedProduct = allSelectedProduct;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartDto other = (CartDto) obj;
		if (cartId != other.cartId)
			return false;
		return true;
	}

}
