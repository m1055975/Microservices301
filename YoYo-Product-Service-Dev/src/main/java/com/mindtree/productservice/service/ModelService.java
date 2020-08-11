package com.mindtree.productservice.service;

import java.util.List;

import com.mindtree.productservice.dto.ProductDto;
import com.mindtree.productservice.exception.service.ServiceException;

public interface ModelService {

	public List<ProductDto> filterByModelService(String modelName) throws ServiceException;

	public List<ProductDto> filterByModelPriceService(String modelName, double minPrice, double maxPrice) throws ServiceException;

}
