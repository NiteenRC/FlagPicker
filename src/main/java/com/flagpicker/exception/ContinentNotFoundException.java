package com.flagpicker.exception;

public class ContinentNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ContinentNotFoundException() {
		super();
	}

	public ContinentNotFoundException(String message) {
		super(message);
	}

	public ContinentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
