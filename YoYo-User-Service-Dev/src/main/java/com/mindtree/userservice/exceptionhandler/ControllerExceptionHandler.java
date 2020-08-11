package com.mindtree.userservice.exceptionhandler;
import java.io.IOException;
import com.mindtree.userservice.exception.InSufficientAmountToBuyYoyoPoints;
import com.mindtree.userservice.exception.InSufficientBalance;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.userservice.controller.UserController;
import com.mindtree.userservice.exception.NoSuchUserFound;
import com.mindtree.userservice.exception.PassWordDidNotMatchException;
import com.mindtree.userservice.exception.ProductBrandNotFound;

@RestControllerAdvice(assignableTypes = UserController.class)
public class ControllerExceptionHandler {
	
	Properties pr = new Properties();
	
	InputStream in = getClass().getClassLoader().getResourceAsStream("error.properties");
	

	@ExceptionHandler(NoSuchUserFound.class)
	public ResponseEntity<java.util.Map<String, Object>> NoSuchUserFound(Exception e, Throwable t) {
		
		try {
			pr.load(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.util.Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("Error", true);
		response.put("Error Message", pr.getProperty("NO_SUCH_USER_FOUND"));
		return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(PassWordDidNotMatchException.class)
	public ResponseEntity<java.util.Map<String, Object>> PassWordDidNotMatchException(Exception e, Throwable t) {
		try {
			pr.load(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.util.Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("Error", true);
		response.put("Error Message", pr.getProperty("PASSWORD_DIDNOT_MATCH"));
		return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InSufficientAmountToBuyYoyoPoints.class)
	public ResponseEntity<java.util.Map<String, Object>> InSufficientAmountToBuyYoyoPoints(Exception e, Throwable t) {
		try {
			pr.load(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.util.Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("Error", true); 
		response.put("Error Message", pr.getProperty("INSUFFICIENT_AMOUNT_TOBUY_POINTS"));
		return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ProductBrandNotFound.class)
	public ResponseEntity<java.util.Map<String, Object>> InvalidProduct(Exception e, Throwable t) {
		try {
			pr.load(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.util.Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("Error", true); 
		response.put("Error Message", "Product Brand not found");
		return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InSufficientBalance.class)
	public ResponseEntity InsufficientBalance(Exception e,Throwable t){
		try {
			pr.load(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("Error", true);
		response.put("Message", pr.getProperty("INSUFFICIENT_BALANCE"));
		return new ResponseEntity(response,HttpStatus.NOT_FOUND);
	}
	
}
