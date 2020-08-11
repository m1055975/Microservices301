package com.mindtree.productservice.service;

import java.util.List;

import com.mindtree.productservice.dto.ProductDto;
import com.mindtree.productservice.exception.service.ServiceException;

public interface CategoryService {

	public List<ProductDto> filterByCategoryService(String categoryName) throws ServiceException;

	public List<ProductDto> filterByCategoryPriceService(String categoryName,double minPrice,double maxPrice) throws ServiceException;

	public List<ProductDto> filterByCategoryBrandPriceService(String categoryName, String brandName, double minPrice,
			double maxPrice) throws ServiceException;

	public List<ProductDto> filterByCategoryBrandService(String categoryName, String brandName) throws ServiceException;

}
