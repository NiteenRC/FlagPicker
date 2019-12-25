package com.flagpicker.exception;

public class CountryFlagNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CountryFlagNotFoundException() {
		super();
	}

	public CountryFlagNotFoundException(String message) {
		super(message);
	}

	public CountryFlagNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
