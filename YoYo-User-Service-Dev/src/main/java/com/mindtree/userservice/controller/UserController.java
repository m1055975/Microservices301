package com.mindtree.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.userservice.dto.AddBalane;
import com.mindtree.userservice.dto.AddYoYoBalanceDto;
import com.mindtree.userservice.dto.LoginDto;
import com.mindtree.userservice.dto.LoginResponseDto;
import com.mindtree.userservice.dto.UpdateProfileDto;
import com.mindtree.userservice.dto.UpdateResponseDto;
import com.mindtree.userservice.dto.UserDto;
import com.mindtree.userservice.dto.UserRegistrationDto;
import com.mindtree.userservice.dto.UserResponseDto;
import com.mindtree.userservice.exception.ServiceException;
import com.mindtree.userservice.exception.UserServiceException;
import com.mindtree.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/userSignUp")
	public ResponseEntity<UserRegistrationDto> userSignUp(@RequestBody UserDto userDto) throws UserServiceException {
		return new ResponseEntity<UserRegistrationDto>(userService.registerUser(userDto), HttpStatus.OK);
	}

	@PutMapping("/addYoyoBalance/{userId}")
	public ResponseEntity<AddYoYoBalanceDto> addYoyoBalance(@RequestBody AddBalane addBalance,@PathVariable String userId) throws UserServiceException {
		return new ResponseEntity<AddYoYoBalanceDto>(userService.addYoyoBalance(addBalance, userId), HttpStatus.OK);
	}

	@PutMapping("/updateProfile/{userId}")
	public ResponseEntity<UpdateResponseDto> updateProfile(@RequestBody UpdateProfileDto updateProfileDto,
			@PathVariable String userId) throws UserServiceException {
		return new ResponseEntity<UpdateResponseDto>(userService.updateProfile(updateProfileDto, userId),HttpStatus.OK);
	}

	@GetMapping("/profileDetail/{userId}")
	public ResponseEntity<UserResponseDto> ProfileDetails(@PathVariable String userId) throws UserServiceException {
		return new ResponseEntity<UserResponseDto>(userService.profileDetails(userId),HttpStatus.OK);
	}
	
	@PostMapping("/loginUser")
	public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginDto loginDto)throws  UserServiceException
	{
		return new ResponseEntity<LoginResponseDto>(userService.loginUser(loginDto),HttpStatus.OK);
	}

	@PutMapping("/verifybalance/{yoyoBalance}/{userId}")
	public ResponseEntity<UserResponseDto> verifyYoyoBalanceController(@PathVariable double yoyoBalance,
			@PathVariable String userId) throws UserServiceException {
		try {
			return new ResponseEntity<UserResponseDto>(userService.verifyYoyoBalance(yoyoBalance,userId),HttpStatus.OK);
		}catch(ServiceException ex) {
			throw new UserServiceException(ex.getMessage());
		}
	}
	
	@PutMapping("/updatebalance/{userId}/{bill}")
	public ResponseEntity<UserResponseDto> updateBalanceController(@PathVariable String userId,@PathVariable double bill){
		UserResponseDto user=userService.updateBalanceController(userId,bill);
		System.out.println(user.getYoyoBalance());
		return new ResponseEntity<UserResponseDto>(user, HttpStatus.OK);
		
	}
	
	//-----------------------Testing update
	@PutMapping("/updateTestbalance/{userId}/{bill}")
	public ResponseEntity<UserResponseDto> updateTestBalanceController(@PathVariable String userId,@PathVariable double bill){
		UserResponseDto user=userService.updateBalanceController(userId,bill);
		System.out.println(user.getYoyoBalance());
		return new ResponseEntity<UserResponseDto>(user, HttpStatus.OK);
	}
	
	

}
