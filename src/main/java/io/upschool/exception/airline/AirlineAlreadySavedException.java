package io.upschool.exception.airline;

public class AirlineAlreadySavedException extends RuntimeException {

	public AirlineAlreadySavedException(String message) {
		super(message);
	}

	public AirlineAlreadySavedException(String message, Throwable cause) {
		super(message, cause);
	}
}
