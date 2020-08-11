package com.mindtree.orderservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindtree.orderservice.dto.ProductDto;
import com.mindtree.orderservice.dto.ResponseDto;

@FeignClient(name="productservice")
public interface ProductProxy {
	
	@PostMapping("filter/verify")
	public ResponseEntity<ResponseDto> verifyProductController(@RequestBody List<ProductDto> productDtoList);
	
	@PostMapping("filter/purchased")
	public ResponseEntity<ResponseDto> purchasedProductController(@RequestBody List<ProductDto> productDtoList);
	
}
