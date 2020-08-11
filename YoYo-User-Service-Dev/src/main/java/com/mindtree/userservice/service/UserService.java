package com.mindtree.userservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

@Service 
public interface UserService {

	UserRegistrationDto registerUser(UserDto userDto);

	AddYoYoBalanceDto addYoyoBalance(AddBalane addBalance, String userId) throws ServiceException;

	UpdateResponseDto updateProfile(UpdateProfileDto updateProfileDto, String userId) throws ServiceException;

	UserResponseDto profileDetails(String userId) throws ServiceException;
	
	LoginResponseDto loginUser(LoginDto loginDto)throws ServiceException;

	UserResponseDto updateBalanceController(String userId,double bill);

	UserResponseDto verifyYoyoBalance(double yoyoBalance, String userId) throws ServiceException;

	
}
