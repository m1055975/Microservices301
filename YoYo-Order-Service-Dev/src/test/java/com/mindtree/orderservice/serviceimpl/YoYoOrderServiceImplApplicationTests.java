//
//package com.mindtree.orderservice.serviceimpl;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.mindtree.orderservice.dto.CartDto;
//import com.mindtree.orderservice.dto.OrderDto;
//import com.mindtree.orderservice.dto.ProductDto;
//import com.mindtree.orderservice.repository.OrderRepository;
//import com.mindtree.orderservice.service.impl.OrderServiceImpl;
//
//@SpringBootTest
//public class YoYoOrderServiceImplApplicationTests {
//
//	@InjectMocks
//	private OrderServiceImpl orderServiceImpl;
//
//	@Mock
//	private OrderRepository orderRepository;
//
//	@Mock
//	private ModelMapper modelMapper;
//
//	private List<ProductDto> productDtos1 = new ArrayList<ProductDto>();
//
//	private ProductDto productDto = new ProductDto();
//	private OrderDto orderDto = new OrderDto();
//	private CartDto cartDto = new CartDto();
//
//	@BeforeEach
//	void setUp() {
//		MockitoAnnotations.initMocks(this);
//		productDtos1 = productsDtos();
//		cartDto = getCartDto();
//		orderDto = getOrderDto();
//
//	}
//
//	private ProductDto getProductDto() {
//		productDto.setProductId(1321);
//		productDto.setProductQuantity(12);
//		productDto.setProductPrice(12628);
//		productDto.setProductDescription("AAAAAAA");
//
//		return productDto;
//	}
//
//	private CartDto getCartDto() {
//		cartDto.setCartId(1);
//		cartDto.setUserId("Annat25");
//		cartDto.setPaymentStatus(true);
//		cartDto.setRedeemCode("Anaw1872");
//		cartDto.setTotalAmount(12651);
//
//		List<ProductDto> productDtos = new ArrayList<ProductDto>();
//		productDtos.add(getProductDto());
//		cartDto.setAllSelectedProduct(productDtos);
//		return cartDto;
//
//	}
//
//	private List<ProductDto> productsDtos() {
//		productDtos1.add(getProductDto());
//		return productDtos1;
//	}
//
//	private OrderDto getOrderDto() {
//		orderDto.setRedeemCode("AFAh1261");
//		orderDto.setPurchaseDate(new Date(System.currentTimeMillis()));
//		return orderDto;
//	}
//
//	@Test
//	public void getGiftController() {
//		when(orderServiceImpl.purchaseProductService(cartDto)).thenReturn(orderDto);
//		OrderDto orderDto1 = orderServiceImpl.purchaseProductService(cartDto);
//		assertEquals(orderDto, orderDto1);
//	}
//
//}
