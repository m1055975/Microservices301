package com.mindtree.productservice.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "model")
public class Model {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int modelId;
	private String modelName;
	@ManyToOne(cascade = CascadeType.ALL)
	private Brand modelBrand;
	@OneToMany(mappedBy="productModel",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Product> productList;

	public Model() {
		super();
		// constructor
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Brand getModelBrand() {
		return modelBrand;
	}

	public void setModelBrand(Brand modelBrand) {
		this.modelBrand = modelBrand;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

}
