package com.mindtree.orderservice.exception.service;

import com.mindtree.orderservice.exception.OrderApplicationException;

public class ServiceException extends OrderApplicationException {

	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
