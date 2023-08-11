package io.upschool.exception;

public class RouteAlreadySavedException extends RuntimeException {
	public RouteAlreadySavedException(String message) {
		super(message);
	}

	public RouteAlreadySavedException(String message, Throwable cause) {
		super(message, cause);
	}
}
