package com.mindtree.productservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ModelDto {
	private int modelId;
	private String modelName;
	@JsonIgnoreProperties("modelList")
	private BrandDto modelBrand;
	@JsonIgnore
	private List<ProductDto> productList;

	public ModelDto() {
		super();
		//constructor
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

	public BrandDto getModelBrand() {
		return modelBrand;
	}

	public void setModelBrand(BrandDto modelBrand) {
		this.modelBrand = modelBrand;
	}

	public List<ProductDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}

}
