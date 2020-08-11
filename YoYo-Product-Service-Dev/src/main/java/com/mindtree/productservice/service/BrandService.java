package com.mindtree.productservice.service;

import java.util.List;

import com.mindtree.productservice.dto.ProductDto;
import com.mindtree.productservice.exception.service.ServiceException;

public interface BrandService {

	public List<ProductDto> filterByBrandService(String brandName) throws ServiceException;

	public List<ProductDto> filterByBrandModelService(String brandName, String modelName) throws ServiceException;

	public List<ProductDto> filterByBrandModelPriceService(String brandName, String modelName, double minPrice,
			double maxPrice) throws ServiceException;

	public List<ProductDto> filterByBrandPriceService(String brandName, double minPrice, double maxPrice) throws ServiceException;

}
