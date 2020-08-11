package com.mindtree.cartservice.dto;

import java.util.List;

import com.mindtree.cartservice.entity.Product;

public class CartDto {
	
	private int cartId;
	
	private String userId;
	
	private double totalAmount;
	
	private boolean paymentStatus;
	
	private List<Product> allSelectedProduct;

	public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartDto(int cartId, String userId, double totalAmount, boolean paymentStatus,
			List<Product> allSelectedProduct) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.paymentStatus = paymentStatus;
		this.allSelectedProduct = allSelectedProduct;
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

	public List<Product> getAllSelectedProduct() {
		return allSelectedProduct;
	}

	public void setAllSelectedProduct(List<Product> allSelectedProduct) {
		this.allSelectedProduct = allSelectedProduct;
	}

	@Override
	public String toString() {
		return "CartDto [cartId=" + cartId + ", userId=" + userId + ", totalAmount=" + totalAmount + ", paymentStatus="
				+ paymentStatus + ", allSelectedProduct=" + allSelectedProduct + "]";
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
