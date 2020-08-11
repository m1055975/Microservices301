package com.mindtree.productservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product implements Comparable<Product> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	private double productPrice;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Model productModel;
	private int productQuantity;
	private String productDescription;

	public Product() {
		super();
		// constructor
	}
	
	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
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

	public Model getProductModel() {
		return productModel;
	}

	public void setProductModel(Model productModel) {
		this.productModel = productModel;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public int compareTo(Product arg0) {
		return (int) (this.productPrice-arg0.productPrice);
	}



}
