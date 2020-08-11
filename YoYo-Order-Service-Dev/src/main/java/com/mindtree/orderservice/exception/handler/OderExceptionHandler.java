package com.mindtree.orderservice.exception.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.orderservice.exception.OrderApplicationException;


@RestControllerAdvice
public class OderExceptionHandler {
	@ExceptionHandler(OrderApplicationException.class)
	protected ResponseEntity handlerAdvice(final OrderApplicationException exception, final HttpServletRequest request) {
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
