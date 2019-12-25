package com.flagpicker.exception;

public class CountryNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CountryNotFoundException() {
		super();
	}

	public CountryNotFoundException(String message) {
		super(message);
	}

	public CountryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
