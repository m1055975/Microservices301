package com.mindtree.cartservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.mindtree.cartservice.dto.CartDto;
import com.mindtree.cartservice.dto.ProductDto;
import com.mindtree.cartservice.entity.Cart;
import com.mindtree.cartservice.entity.Product;
import com.mindtree.cartservice.exception.service.ServiceException;
import com.mindtree.cartservice.repository.CartRepository;
import com.mindtree.cartservice.repository.ProductRepository;
import com.mindtree.cartservice.service.impl.CartServiceImpl;

public class CartServiceTest {

	@InjectMocks
	CartServiceImpl cartService;
	
	@Mock
	CartRepository cartRepository;
	
	@Mock
	ProductRepository productRepository;
	
	@Mock
	ModelMapper modelMapper;
	
	private String userId = "user";
	
	List<Cart> cartList = new ArrayList<Cart>();
	Set<ProductDto> productDtoSet = new HashSet<ProductDto>();
	Set<Product> productSet = new HashSet<Product>();
	
	@BeforeEach
	void setup()
	{
		MockitoAnnotations.initMocks(this);
		
		cartList.add(getCart());
		
		productDtoSet.add(getProductDto1());
		productDtoSet.add(getProductDto2());
		
		productSet.add(getProduct1());
		productSet.add(getProduct2());
	}
	
	@Test
	void testAddToCart() throws ServiceException
	{
		String userId = "Anjali@123";
		
		when(cartRepository.findAllByUserId(userId)).thenReturn(cartList);
		when(modelMapper.map(getProductDto1(), Product.class)).thenReturn(getProduct1());

		assumeTrue(cartList.isEmpty());
		when(cartService.newCart(productSet, userId)).thenReturn(getCartDto());
		
		when(cartService.existingCart(productSet, getCart(), userId)).thenReturn(getCartDto());
		
		assertEquals(getCartDto(), cartService.addToCart(productDtoSet, userId));
	}
	
	@Test
	void testAddToCartException() throws ServiceException
	{
		String userId = "Anjali@123";
		
		when(cartRepository.findAllByUserId(userId)).thenReturn(cartList);
		when(modelMapper.map(getProductDto1(), Product.class)).thenReturn(getProduct1());

		assumeTrue(cartList.isEmpty());
		when(cartService.newCart(productSet, userId)).thenReturn(getCartDto());
		
		assertEquals(getCartDto(), cartService.addToCart(productDtoSet, userId));
		
		when(cartService.existingCart(productSet, getCart(), userId)).thenReturn(getCartDto());
		
		assertThrows(ServiceException.class,()->cartService.addToCart(productDtoSet, userId));
	}
	
	@Test
	void testExistingCart() throws ServiceException
	{
		Set<Product> productSet = new HashSet<Product>();
		productSet = getCart().getAllSelectedProduct();
		
		assumeFalse(getCart().getAllSelectedProduct().contains(getProduct1()));
		
		when(modelMapper.map(cartRepository.saveAndFlush(getCart()), CartDto.class)).thenReturn(getCartDto());
		assertEquals(getCartDto(), cartService.existingCart(productSet, getCart(), getCart().getUserId()));
	}
	
	@Test
	void testExistingCartException() throws ServiceException
	{
		Set<Product> productSet = getCart().getAllSelectedProduct();
		
		assumeTrue(getProduct1().getQuantity() > 0);
		
		when(modelMapper.map(cartRepository.saveAndFlush(getCart()), CartDto.class)).thenReturn(getCartDto());
		assertThrows(ServiceException.class,()->cartService.existingCart(productSet, getCart(), getCart().getUserId()));
	}
	
	@Test
	void testNewCart() throws ServiceException 
	{
		assumeFalse(getProduct1().getQuantity() > 0);
		when(modelMapper.map(cartRepository.save(getCart()), CartDto.class)).thenReturn(getCartDto());
		
		assertEquals(getCartDto(), cartService.newCart(productSet, userId));
	}
	
	@Test
	void testNewCartException() throws ServiceException 
	{
		assumeFalse(getProduct1().getQuantity() > 0);
		when(modelMapper.map(cartRepository.save(getCart()), CartDto.class)).thenReturn(getCartDto());
		
		assertThrows(ServiceException.class,()->cartService.newCart(productSet, getCart().getUserId()));
	}
		
	@Test
	void testRemoveFromCart() throws ServiceException
	{
		when(cartRepository.findAllByUserId(getCart().getUserId())).thenReturn(cartList);
		assumeFalse(cartList.isEmpty());
		
		assumeFalse(getCart().isPaymentStatus());
		assumeTrue(getCart().getUserId().equals(getCart().getUserId()));
		
		assumeFalse(getCart().getAllSelectedProduct().isEmpty());
		assumeTrue(getProduct1().getProductId() == getProduct1().getProductId());
		assumeTrue(getProduct1().getUserId().equals(getProduct1().getUserId()));
			
		when(modelMapper.map(getProduct1(), ProductDto.class)).thenReturn(getProductDto1());
		
		Set<ProductDto> productSet = new HashSet<ProductDto>();
		
		getProductDto1().setCart(cartList.stream().collect(Collectors.toSet()));
		productSet.add(getProductDto1());
		
		assertEquals(productSet, cartService.removeFromCart(productDtoSet, getCart().getUserId()));
	}
	
	@Test
    void testRemoveFromCartException() throws ServiceException
    {
        when(cartRepository.findAllByUserId(getCart().getUserId())).thenReturn(cartList);
        assumeFalse(cartList.isEmpty());
       
        assumeFalse(getCart().isPaymentStatus());
        assumeTrue(getCart().getUserId().equals(getCart().getUserId()));
       
        assumeFalse(getCart().getAllSelectedProduct().isEmpty());
        assumeTrue(getProduct1().getProductId() == getProduct1().getProductId());
        assumeTrue(getProduct1().getUserId().equals(getProduct1().getUserId()));
           
        when(modelMapper.map(getProduct1(), ProductDto.class)).thenReturn(getProductDto1());
       
        Set<ProductDto> productSet = new HashSet<ProductDto>();
       
        getProductDto1().setCart(cartList.stream().collect(Collectors.toSet()));
        productSet.add(getProductDto1());
       
        assertThrows(ServiceException.class,()->cartService.removeFromCart(productDtoSet, userId));
    }
	
	
	@Test
	void testIncreaseQuantity() throws ServiceException
	{
		when(modelMapper.map(getProduct1(), ProductDto.class)).thenReturn(getProductDto1());
		assumeTrue(cartList.isEmpty());

		assumeFalse(getCart().isPaymentStatus());
		assumeTrue(getCart().getUserId().equals(getCart().getUserId()));
		
		assumeTrue(getProduct1().getQuantity() > 0);
		
		when(modelMapper.map(cartRepository.saveAndFlush(getCart()), CartDto.class)).thenReturn(getCartDto());
		
		assertEquals(getCartDto(), cartService.increaseQuantity(productDtoSet, getCart().getUserId()));
	}
	
	@Test
	void testIncreaseQuantityException() throws ServiceException
	{
		when(modelMapper.map(getProduct1(), ProductDto.class)).thenReturn(getProductDto1());
		assumeTrue(cartList.isEmpty());

		assumeFalse(getCart().isPaymentStatus());
		assumeTrue(getCart().getUserId().equals(getCart().getUserId()));
		
		assumeTrue(getProduct1().getQuantity() > 0);
		
		when(modelMapper.map(cartRepository.saveAndFlush(getCart()), CartDto.class)).thenReturn(getCartDto());
		
		assertThrows(ServiceException.class,()->cartService.increaseQuantity(productDtoSet, getCart().getUserId()));
	}

	
	@Test
	void testDecreaseQuantity() throws ServiceException
	{
		when(modelMapper.map(getProduct1(), Product.class)).thenReturn(getProduct1());
		when(cartRepository.findAllByUserId(getCart().getUserId())).thenReturn(cartList);
		
		assumeFalse(cartList.isEmpty());
		assumeFalse(getCart().isPaymentStatus());
		assumeTrue(getCart().getUserId().equals(getCart().getUserId()));
		
		assumeTrue(getCart().getAllSelectedProduct().isEmpty());
		
		assumeTrue(getProduct1().getProductId() == getProduct1().getProductId());
		assumeTrue(getProduct1().getUserId().equals(getProduct1().getUserId()));
		
		assumeTrue(getProduct1().getQuantity() > 0);
		when(modelMapper.map(cartRepository.saveAndFlush(getCart()), CartDto.class)).thenReturn(getCartDto());
		
		assertEquals(getCartDto(), cartService.decreaseQuantity(productDtoSet, getCart().getUserId()));
	}
	
	@Test
	void testDecreaseQuantityException() throws ServiceException
	{
		when(modelMapper.map(getProduct1(), Product.class)).thenReturn(getProduct1());
		when(cartRepository.findAllByUserId(getCart().getUserId())).thenReturn(cartList);
		
		assumeFalse(cartList.isEmpty());
		assumeFalse(getCart().isPaymentStatus());
		assumeTrue(getCart().getUserId().equals(getCart().getUserId()));
		
		assumeTrue(getCart().getAllSelectedProduct().isEmpty());
		
		assumeTrue(getProduct1().getProductId() == getProduct1().getProductId());
		assumeTrue(getProduct1().getUserId().equals(getProduct1().getUserId()));
		
		assumeTrue(getProduct1().getQuantity() > 0);
		when(modelMapper.map(cartRepository.saveAndFlush(getCart()), CartDto.class)).thenReturn(getCartDto());
		
		assertThrows(ServiceException.class,()->cartService.decreaseQuantity(productDtoSet, getCart().getUserId()));
	}
	
	@Test
	void testEmptyMyCart() throws ServiceException
	{
		when(cartRepository.findAllByUserId(getCart().getUserId())).thenReturn(cartList);
		assumeFalse(cartList.isEmpty());
		
		assumeFalse(getCart().isPaymentStatus());
		assumeTrue(getCart().getUserId().equals(getCart().getUserId()));
		
		assumeTrue(getCart() != null);
		assumeFalse(getCart().isPaymentStatus());
		assumeTrue(getCart().getAllSelectedProduct().isEmpty());
		
		when(modelMapper.map(getCart(), CartDto.class)).thenReturn(getCartDto());
		assertEquals(getCartDto(), cartService.emptyMyCart(getCart().getUserId()));
	}
	
	@Test
	void testEmptyMyCartException() throws ServiceException
	{
		when(cartRepository.findAllByUserId(getCart().getUserId())).thenReturn(cartList);
		assumeFalse(cartList.isEmpty());
		
		assumeFalse(getCart().isPaymentStatus());
		assumeTrue(getCart().getUserId().equals(getCart().getUserId()));
		
		assumeTrue(getCart() != null);
		assumeFalse(getCart().isPaymentStatus());
		assumeTrue(getCart().getAllSelectedProduct().isEmpty());
		
		when(modelMapper.map(getCart(), CartDto.class)).thenReturn(getCartDto());
		assertThrows(ServiceException.class,()-> cartService.emptyMyCart(getCart().getUserId()));
	}
	
	@Test
	void testGetCart() throws ServiceException
	{
		when(cartRepository.findAll()).thenReturn(cartList);
		assumeFalse(getCart().isPaymentStatus());
		assumeTrue(getCart().getUserId().equals(getCart().getUserId()));
		
		assumeFalse(getCart().isPaymentStatus());
		assumeTrue(getCart().getUserId() != null);
		assumeFalse(getCart().getAllSelectedProduct().isEmpty());
		
		when(modelMapper.map(getCart(), CartDto.class)).thenReturn(getCartDto());
		assertEquals(getCartDto(), cartService.getCart(getCart().getUserId()));
	}
	
	@Test
    void testGetCartException() throws ServiceException
    {
        when(cartRepository.findAll()).thenReturn(cartList);
        assumeFalse(getCart().isPaymentStatus());
        assumeTrue(getCart().getUserId().equals(getCart().getUserId()));
       
        assumeFalse(getCart().isPaymentStatus());
        assumeTrue(getCart().getUserId() != null);
        assumeFalse(getCart().getAllSelectedProduct().isEmpty());
       
        when(modelMapper.map(getCart(), CartDto.class)).thenReturn(getCartDto());
        assertThrows(ServiceException.class,()->cartService.getCart(userId));
    }
	
	@Test
	void testchangePaymentStatus() throws ServiceException
	{
		assumeTrue(cartRepository.existsById(getCart().getCartId()));
		when(cartRepository.findById(getCart().getCartId()).get()).thenReturn(getCart());
		
		assertEquals("Payment status changed successfully", cartService.changePaymentStatus(getCart().getCartId()));
	}
	
	@Test
	void testchangePaymentStatusException() throws ServiceException
	{
		assumeTrue(cartRepository.existsById(getCart().getCartId()));
		when(cartRepository.findById(getCart().getCartId()).get()).thenReturn(getCart());
		
		assertThrows(ServiceException.class,()->cartService.changePaymentStatus(getCart().getCartId()));
	}
	
	private Cart getCart()
	{
		Cart cart = new Cart();
		cart.setCartId(1);
		cart.setUserId("Anjali@123");
		cart.setTotalAmount(100.0);
		cart.setPaymentStatus(false);
		cart.setAllSelectedProduct(productSet);
		
		return cart;
	}
	
	private CartDto getCartDto()
	{
		CartDto cartDto = new CartDto();
		cartDto.setCartId(1);
		cartDto.setUserId("Anjali@123");
		cartDto.setTotalAmount(100.0);
		
		List<Product> productList = productSet.stream().collect(Collectors.toList());
		cartDto.setAllSelectedProduct(productList);
		cartDto.setPaymentStatus(false);
		
		return cartDto;
	}
	
	private ProductDto getProductDto1()
	{
		ProductDto productDto1 = new ProductDto();
		productDto1.setProductId(1);
		productDto1.setProductName("Cello");
		productDto1.setProductPrice(10.0);
		productDto1.setQuantity(4);
		productDto1.setUserId("Anjali@123");
		productDto1.setCart(null);
		
		return productDto1;
	}
	
	private ProductDto getProductDto2()
	{
		ProductDto productDto2 = new ProductDto();
		productDto2.setProductId(2);
		productDto2.setProductName("Renolds");
		productDto2.setProductPrice(10.0);
		productDto2.setQuantity(4);
		productDto2.setUserId("Anjali@123");
		productDto2.setCart(null);
		
		return productDto2;
	}
	
	private Product getProduct1()
	{
		Product product1 = new Product();
		product1.setCart(null);
		product1.setProductId(1);
		product1.setProductName("Cello");
		product1.setProductPrice(10.0);
		product1.setQuantity(4);
		product1.setUserId("Anjali@123");
		
		return product1;
	}
	
	private Product getProduct2()
	{
		Product product2 = new Product();
		product2.setCart(null);
		product2.setProductId(1);
		product2.setProductName("Renolds");
		product2.setProductPrice(10.0);
		product2.setQuantity(4);
		product2.setUserId("Anjali@123");
		
		return product2;
	}
	
}
