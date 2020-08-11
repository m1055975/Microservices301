package com.mindtree.orderservice.dto;

import java.util.Date;
import java.util.List;

import com.mindtree.orderservice.entity.Product;

public class OrderDto {
	private Date purchaseDate;
	private String redeemCode;
	private List<Product> allSelectedProduct;
	
	public OrderDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getRedeemCode() {
		return redeemCode;
	}
	public void setRedeemCode(String redeemCode) {
		this.redeemCode = redeemCode;
	}
	public List<Product> getProductList() {
		return allSelectedProduct;
	}
	public void setProductList(List<Product> allSelectedProduct) {
		this.allSelectedProduct = allSelectedProduct;
	}
	
}
