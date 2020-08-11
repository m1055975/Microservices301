package com.mindtree.productservice.exception.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.productservice.dto.ProductDto;
import com.mindtree.productservice.exception.ProductException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	@ExceptionHandler(ProductException.class)
	protected ResponseEntity handlerAdvice(final ProductException exception, final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		Properties properties = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("error.properties");
		try {
			properties.load(input);
		} catch (IOException e) {
			System.out.println("some error occured while loading with input");
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					System.out.println("some error occured while closing input");
				}
			}
		}
		error.setMessage(properties.getProperty(exception.getMessage()));
		error.setPath(request.getRequestURI());
		return new ResponseEntity(error.getMessage(), HttpStatus.NOT_FOUND);

	}
}
