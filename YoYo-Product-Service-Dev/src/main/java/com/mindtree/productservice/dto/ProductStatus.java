package com.mindtree.productservice.dto;

public class ProductStatus {
	private int productId;
	private double productPrice;
	private String productDescription;
	private int productQuantity;
	private String status;
	private ModelDto productModel;
	
	public ProductStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public ModelDto getProductModel() {
		return productModel;
	}
	public void setProductModel(ModelDto productModel) {
		this.productModel = productModel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
