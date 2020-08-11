package com.mindtree.orderservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.orderservice.dto.CartDto;
import com.mindtree.orderservice.dto.MessageBody;
import com.mindtree.orderservice.dto.ProductDto;
import com.mindtree.orderservice.dto.ResponseDto;
import com.mindtree.orderservice.dto.UserResponseDto;
import com.mindtree.orderservice.feign.CartProxy;
import com.mindtree.orderservice.feign.ProductProxy;
import com.mindtree.orderservice.feign.RedeemProxy;
import com.mindtree.orderservice.feign.UserProxy;
import com.mindtree.orderservice.service.OrderService;

import feign.FeignException;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;

	@Autowired
	ProductProxy productProxy;

	@Autowired
	UserProxy userProxy;
	
	@Autowired
	RedeemProxy redeemProxy;
	
	@Autowired
	CartProxy cartProxy;

	@PostMapping("/purchase")
	ResponseEntity purchaseProductController(@RequestBody CartDto cartDto) {
		try {
			List<ProductDto> productDtoList = cartDto.getAllSelectedProduct().stream().collect(Collectors.toList());

//			*verify products
			ResponseDto responseDto= productProxy.verifyProductController(productDtoList).getBody();
			System.out.println("product verification done");
			
//			* verify yoyoBalance
			UserResponseDto userResponseDto = userProxy.verifyYoyoBalanceController(cartDto.getTotalAmount(), cartDto.getUserId()).getBody();
			System.out.println("verify balance and updated");

//			* updating redeemCode
			responseDto = productProxy.purchasedProductController(productDtoList).getBody();
			System.out.println("product updated");
			
//			* update yoyoBalance
			//userResponseDto=userProxy.updateBalanceController(cartDto.getUserId(),cartDto.getTotalAmount()).getBody();
			//System.out.println(userResponseDto);
//			
//			*Generate redeemcode
			String redeemCode=redeemProxy.generateRedeemCode(cartDto.getUserId(), cartDto.getCartId()).getBody();
			cartDto.setRedeemCode(redeemCode);
			System.out.println("code generated");
		   
//			* update cart
			String status=cartProxy.setPaymentStatus(cartDto.getCartId()).getBody();
		   System.out.println("cart deleted");

		  //			*email
		   orderService.emailService(cartDto,new MessageBody(cartDto.getRedeemCode(),"52.170.27.18:8082/redeemProduct/{userId}/{redeemId}"));
			return new ResponseEntity(orderService.purchaseProductService(cartDto), HttpStatus.OK);
		} catch (FeignException e) {
		HttpStatus status = HttpStatus.resolve(e.status());
			if (status == null) {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			return new ResponseEntity(e.contentUTF8(), status);
		}
	}
	
	@GetMapping("/giftlist/{redeemCode}")
	public List<ProductDto> getGiftController(@PathVariable String redeemCode) {
		return orderService.getGiftService(redeemCode);
	}
	
	
}
