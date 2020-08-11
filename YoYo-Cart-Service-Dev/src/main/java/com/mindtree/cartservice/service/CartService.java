package com.mindtree.cartservice.service;

import java.util.Set;

import com.mindtree.cartservice.dto.CartDto;
import com.mindtree.cartservice.dto.ProductDto;
import com.mindtree.cartservice.exception.service.ServiceException;

public interface CartService {
   
	CartDto addToCart(Set<ProductDto> productDtoSet, String userId) throws ServiceException;

	Set<ProductDto> removeFromCart(Set<ProductDto> productDtoSet, String userId) throws ServiceException;

    CartDto getCart(String userId) throws ServiceException;

	CartDto emptyMyCart(String userId) throws ServiceException;

	CartDto increaseQuantity(Set<ProductDto> productDtoSet, String userId) throws ServiceException;

	CartDto decreaseQuantity(Set<ProductDto> productDtoSet, String userId) throws ServiceException;

	String changePaymentStatus(int cartId) throws ServiceException;
	
}
