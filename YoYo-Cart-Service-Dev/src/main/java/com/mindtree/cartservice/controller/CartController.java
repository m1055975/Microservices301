package com.mindtree.cartservice.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.cartservice.dto.CartDto;
import com.mindtree.cartservice.dto.ProductDto;
import com.mindtree.cartservice.exception.CartServiceApplicationException;
import com.mindtree.cartservice.exception.service.ServiceException;
import com.mindtree.cartservice.service.CartService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/cart")
@Api(value = "cart",description = "Api to perform cart related operations")
public class CartController {

	@Autowired
	CartService cartService;

//add items to cart
	@PostMapping("/add/{userId}")
	public ResponseEntity<CartDto> addToCart(@PathVariable String userId, @RequestBody Set<ProductDto> productDto)
			throws CartServiceApplicationException {
		CartDto response;
		try {
			response = cartService.addToCart(productDto, userId);
			return new ResponseEntity<CartDto>(response, HttpStatus.OK);
		} catch (ServiceException e) {
			throw new CartServiceApplicationException(e.getMessage());
		}
	}

//update to increase items in cart
	@PutMapping("/update/increase/{userId}")
	public ResponseEntity<CartDto> increaseItemsInCart(@PathVariable String userId,
			@RequestBody Set<ProductDto> productDtoSet) throws CartServiceApplicationException {
		try {
			return new ResponseEntity<CartDto>(cartService.increaseQuantity(productDtoSet, userId), HttpStatus.OK);
		} catch (ServiceException e) {
			throw new CartServiceApplicationException(e.getMessage());
		}
	}
	 
//update to decrease items in cart
	@PutMapping("/update/decrease/{userId}")
	public ResponseEntity<CartDto> decreaseItemsInCart(@PathVariable String userId,
			@RequestBody Set<ProductDto> productDtoSet) throws CartServiceApplicationException {
		try {
			return new ResponseEntity<CartDto>(cartService.decreaseQuantity(productDtoSet, userId), HttpStatus.OK);
		} catch (ServiceException e) {
			throw new CartServiceApplicationException(e.getMessage());
		}
	}

	
//get list of products in cart for a user
	@GetMapping("/getcart/{userId}")
	public ResponseEntity<CartDto> getCart(@PathVariable String userId) throws CartServiceApplicationException {
		try 
		{
			return new ResponseEntity<CartDto>(cartService.getCart(userId), HttpStatus.OK);
		}
		catch (ServiceException e) {
		 throw new CartServiceApplicationException(e.getMessage());
		}
	}

//empty the cart of a user
	@DeleteMapping("/emptycart/{userId}")
	public ResponseEntity<CartDto> emptyMyCart(@PathVariable String userId) throws CartServiceApplicationException {
		try 
		{
		 return new ResponseEntity<CartDto>(cartService.emptyMyCart(userId), HttpStatus.OK); 
		}
		catch (ServiceException e) 
		{
		 throw new CartServiceApplicationException(e.getMessage());
		}
	}
	
//empty the cart of a user
		@DeleteMapping("/remove/{userId}")
		public ResponseEntity<Set<ProductDto>> removeFromCart(@PathVariable String userId,@RequestBody Set<ProductDto> productDtoSet) throws CartServiceApplicationException {
			try 
			{
			 return new ResponseEntity<Set<ProductDto>>(cartService.removeFromCart(productDtoSet, userId), HttpStatus.OK);
			}
			catch (ServiceException e) 
			{
				throw new CartServiceApplicationException(e.getMessage());
			}
		}
		
// set payment status
		@PutMapping("/status/{cartId}")
		public ResponseEntity<String> setPaymentStatus(@PathVariable int cartId) throws CartServiceApplicationException {
			try 
			{
			 return new ResponseEntity<String>(cartService.changePaymentStatus(cartId), HttpStatus.OK);
			}
			catch (ServiceException e) 
			{
				throw new CartServiceApplicationException(e.getMessage());
			}
		}
		
}
