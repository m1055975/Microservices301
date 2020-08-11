package com.mindtree.orderservice.service;

import java.util.List;

import com.mindtree.orderservice.dto.CartDto;
import com.mindtree.orderservice.dto.MessageBody;
import com.mindtree.orderservice.dto.OrderDto;
import com.mindtree.orderservice.dto.ProductDto;

public interface OrderService {

	OrderDto purchaseProductService(CartDto cartDto);

	List<ProductDto> getGiftService(String redeemCode);

	void emailService(CartDto cartDto,MessageBody messageBody);

}
