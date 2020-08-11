package com.mindtree.orderservice.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
public class Order {
	
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private long orderId;
	private String userId;
	private Date purchaseDate;
	private Date redeemDate;
	private String redeemCode;
	private List<Product> allSelectedProduct;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getRedeemCode() {
		return redeemCode;
	}
	public void setRedeemCode(String redeemCode) {
		this.redeemCode = redeemCode;
	}
	public List<Product> getAllSelectedProduct() {
		return allSelectedProduct;
	}



	public void setAllSelectedProduct(List<Product> allSelectedProduct) {
		this.allSelectedProduct = allSelectedProduct;
	}



	public List<Product> getProductList() {
		return allSelectedProduct;
	}

	public void setProductList(List<Product> allSelectedProduct) {
		this.allSelectedProduct = allSelectedProduct;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getRedeemDate() {
		return redeemDate;
	}

	public void setRedeemDate(Date redeemDate) {
		this.redeemDate = redeemDate;
	}

}
