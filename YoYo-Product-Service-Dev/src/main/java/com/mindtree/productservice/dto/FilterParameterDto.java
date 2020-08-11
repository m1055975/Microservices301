package com.mindtree.productservice.dto;

import java.util.List;

public class FilterParameterDto {
	private double productId;
	private String categoryName;
	private String brandName;
	private String modelName;
	private double minPrice;
	private double maxPrice;

	public FilterParameterDto() {
		super();
		
	}

	public FilterParameterDto(double productId, String categoryName, String brandName, String modelName,
			double minPrice, double maxPrice) {
		super();
		this.productId = productId;
		this.categoryName = categoryName;
		this.brandName = brandName;
		this.modelName = modelName;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public double getProductId() {
		return productId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getModelName() {
		return modelName;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

}
