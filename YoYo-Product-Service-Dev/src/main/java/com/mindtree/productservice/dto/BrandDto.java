package com.mindtree.productservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BrandDto {
	private int brandId;
	private String brandName;
	private CategoryDto brandCategory;
	@JsonIgnore
	private List<ModelDto> ModelList;

	public BrandDto() {
		super();
		// constructor
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public CategoryDto getBrandCategory() {
		return brandCategory;
	}

	public void setBrandCategory(CategoryDto brandCategory) {
		this.brandCategory = brandCategory;
	}

	public List<ModelDto> getModelList() {
		return ModelList;
	}

	public void setModelList(List<ModelDto> modelList) {
		ModelList = modelList;
	}

}
