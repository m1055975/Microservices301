package com.mindtree.cartservice.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@IdClass(ProductKey.class)
@Table(name = "cartProduct")
public class Product {
	
	@Id
	private int productId;
   
	@Id
	private String userId;
	
	private double productPrice;
  
	private String productName;
	
	private int quantity;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Cart> cart;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int productId, String userId, double productPrice, String productName, int quantity,
			Set<Cart> cart) {
		super();
		this.productId = productId;
		this.userId = userId;
		this.productPrice = productPrice;
		this.productName = productName;
		this.quantity = quantity;
		this.cart = cart;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Set<Cart> getCart() {
		return cart;
	}

	public void setCart(Set<Cart> cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", userId=" + userId + ", productPrice=" + productPrice
				+ ", productName=" + productName + ", quantity=" + quantity + ", cart=" + cart + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Product other = (Product) obj;
		if (productId != other.productId)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	
}
