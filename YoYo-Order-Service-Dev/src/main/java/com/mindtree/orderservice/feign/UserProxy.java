package com.mindtree.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.mindtree.orderservice.dto.UserResponseDto;


@FeignClient(name = "userservice")
public interface UserProxy {

	@PutMapping("/user/verifybalance/{yoyoBalance}/{userId}")  
	public ResponseEntity<UserResponseDto> verifyYoyoBalanceController(@PathVariable double yoyoBalance,
			@PathVariable String userId);
}
