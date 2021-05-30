package com.ecommerce.exception;

public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 752858877580882829L;

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
