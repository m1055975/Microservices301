package com.mindtree.cartservice.exception.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.cartservice.exception.CartServiceApplicationException;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(CartServiceApplicationException.class)
	protected ExceptionResponse handlerAdvice(final CartServiceApplicationException exception, final HttpServletRequest request) {
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
		return error;

	}

}
