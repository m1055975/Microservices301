package com.mindtree.cartservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.mindtree.cartservice.dto.CartDto;
import com.mindtree.cartservice.dto.ProductDto;
import com.mindtree.cartservice.exception.CartServiceApplicationException;
import com.mindtree.cartservice.exception.service.ServiceException;
import com.mindtree.cartservice.service.CartService;

public class CartControllerTest {

	@InjectMocks
	CartController cartController;
	
	@Mock
	CartService cartService;
	
	private String userId = "user";
	
	private CartDto cartDto;
	private Set<ProductDto> productSet = new HashSet<ProductDto>();
	
	@BeforeEach
	void setup() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		
		cartDto = getCart();
		
		productSet.add(getProduct1());
		productSet.add(getProduct2());
	}
	
	@Test
	void testAddToCart() throws CartServiceApplicationException
	{		
		when(cartService.addToCart(productSet, cartDto.getUserId())).thenReturn(cartDto);
		ResponseEntity<CartDto> result = cartController.addToCart(cartDto.getUserId(), productSet);
		
		assertEquals(cartDto, result.getBody());
	}
	
	@Test
	void testAddToCartException() throws CartServiceApplicationException
	{		
		when(cartService.addToCart(productSet, userId)).thenThrow(ServiceException.class);
		
		assertThrows(CartServiceApplicationException.class,()->cartController.addToCart(userId, productSet));
	}
	
	@Test
	void testIncreaseItemsInCart() throws CartServiceApplicationException
	{
		when(cartService.increaseQuantity(productSet, getCart().getUserId())).thenReturn(getCart());
		ResponseEntity<CartDto> result = cartController.increaseItemsInCart(getCart().getUserId(), productSet);
		
		assertEquals(getCart(), result.getBody());
	}
	
	@Test
	void testIncreaseItemsInCartException() throws CartServiceApplicationException
	{
		when(cartService.increaseQuantity(productSet, "user")).thenThrow(ServiceException.class);
		
		assertThrows(CartServiceApplicationException.class,()->cartController.increaseItemsInCart(userId, productSet));
	}
	
	@Test
	void testDecreaseItemsInCart() throws CartServiceApplicationException
	{
		when(cartService.decreaseQuantity(productSet, getCart().getUserId())).thenReturn(getCart());
		ResponseEntity<CartDto> result = cartController.decreaseItemsInCart(getCart().getUserId(), productSet);
	
		assertEquals(getCart(), result.getBody());
	}
	
	@Test
	void testDecreaseItemsInCartException() throws CartServiceApplicationException
	{
		when(cartService.decreaseQuantity(productSet, "user")).thenThrow(ServiceException.class);
	
		assertThrows(CartServiceApplicationException.class,()->cartController.decreaseItemsInCart(userId, productSet));
	}
	
	@Test
	void testGetCart() throws CartServiceApplicationException
	{
		when(cartService.getCart(getCart().getUserId())).thenReturn(getCart());
		ResponseEntity<CartDto> result = cartController.getCart(getCart().getUserId());
		
		assertEquals(getCart(), result.getBody());
	}
	
	
	@Test
	void testGetCartException() throws CartServiceApplicationException
	{
		when(cartService.getCart("user")).thenThrow(ServiceException.class);
		assertThrows(CartServiceApplicationException.class,()->cartController.getCart(userId));

	}
	
	@Test
	void testEmptyMyCart() throws CartServiceApplicationException
	{
		when(cartService.emptyMyCart(getCart().getUserId())).thenReturn(getCart());
		ResponseEntity<CartDto> result = cartController.emptyMyCart(getCart().getUserId());
		
		assertEquals(getCart(), result.getBody());
	}
	
	@Test
	void testEmptyMyCartException() throws CartServiceApplicationException
	{
		when(cartService.emptyMyCart("user")).thenThrow(ServiceException.class);
		
		assertThrows(CartServiceApplicationException.class,()->cartController.emptyMyCart(userId));
	}
	
	@Test
	void testRemoveFromCart() throws CartServiceApplicationException 
	{
		when(cartService.removeFromCart(productSet, getCart().getUserId())).thenReturn(null);
		ResponseEntity<Set<ProductDto>> result = cartController.removeFromCart(getCart().getUserId(), productSet);
		
		assertEquals(null, result.getBody());
	}
	
	@Test
	void testRemoveFromCartException() throws CartServiceApplicationException 
	{
		when(cartService.removeFromCart(productSet,"user")).thenThrow(ServiceException.class);
		
		assertThrows(CartServiceApplicationException.class,()->cartController.removeFromCart(userId, productSet));
	}
	
	@Test
	void testSetPaymentStatus() throws CartServiceApplicationException
	{
		when(cartService.changePaymentStatus(cartDto.getCartId())).thenReturn("Payment status changed successfully");
		ResponseEntity<String> result = cartController.setPaymentStatus(cartDto.getCartId());
		
		assertEquals("Payment status changed successfully", result.getBody());
	}
	
	private CartDto getCart()
	{
		CartDto cartDto = new CartDto();
		cartDto.setCartId(1);
		cartDto.setUserId("Anjali@12");
		cartDto.setTotalAmount(100.0);
		cartDto.setAllSelectedProduct(null);
		cartDto.setPaymentStatus(false);
		
		return cartDto;
	}
	
	private ProductDto getProduct1()
	{
		ProductDto product1 = new ProductDto();
		product1.setProductId(1);
		product1.setProductName("Cello");
		product1.setProductPrice(10.0);
		product1.setQuantity(4);
		product1.setUserId("Anjali@12");
		product1.setCart(null);
		
		return product1;
	}
	
	private ProductDto getProduct2()
	{
		ProductDto product2 = new ProductDto();
		product2.setProductId(2);
		product2.setProductName("Renolds");
		product2.setProductPrice(10.0);
		product2.setQuantity(4);
		product2.setUserId("Anjali@12");
		product2.setCart(null);
		
		return product2;
	}
	
}
