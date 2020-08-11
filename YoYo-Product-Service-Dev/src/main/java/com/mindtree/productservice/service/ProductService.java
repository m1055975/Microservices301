package com.mindtree.productservice.service;

import java.util.List;

import com.mindtree.productservice.dto.ProductDto;
import com.mindtree.productservice.dto.ProductStatus;
import com.mindtree.productservice.exception.service.ServiceException;

public interface ProductService {

	public ProductDto getProductService(int productId) throws ServiceException;

	public List<ProductDto> filterByPriceService(double minPrice,double maxPrice) throws ServiceException;

	public List<ProductDto> viewProductService();

	public List<ProductStatus> purchasedProductService(List<ProductDto> productDtoList) throws ServiceException;

	public List<ProductDto> verifyProductService(List<ProductDto> productDtoList) throws ServiceException;

}
