package com.mindtree.cartservice.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cartId")
	private int cartId;
	
	@Column(name = "userId")
	private String userId;
	
	@Column(name = "totalAmount")
	private double totalAmount;
	
	@Column(name = "paymentStatus")
	private boolean paymentStatus;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "cart")
	private Set<Product> allSelectedProduct;

	public Cart() {
		
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int cartId, String userId, double totalAmount, boolean paymentStatus,
			Set<Product> allSelectedProduct) {
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

	public Set<Product> getAllSelectedProduct() {
		return allSelectedProduct;
	}

	public void setAllSelectedProduct(Set<Product> allSelectedProduct) {
		this.allSelectedProduct = allSelectedProduct;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", userId=" + userId + ", totalAmount=" + totalAmount + ", paymentStatus="
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
		Cart other = (Cart) obj;
		if (cartId != other.cartId)
			return false;
		return true;
	}

		
}