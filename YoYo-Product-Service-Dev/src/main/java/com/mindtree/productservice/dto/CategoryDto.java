package com.mindtree.productservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CategoryDto {
	private int categoryId;
	private String categoryName;
	@JsonIgnore
	private List<BrandDto> brandList;
	public CategoryDto() {
		super();
		// constructor
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<BrandDto> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<BrandDto> brandList) {
		this.brandList = brandList;
	}
	
}
