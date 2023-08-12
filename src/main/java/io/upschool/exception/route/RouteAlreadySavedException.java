package io.upschool.exception.route;

public class RouteAlreadySavedException extends RuntimeException {
	public RouteAlreadySavedException(String message) {
		super(message);
	}

	public RouteAlreadySavedException(String message, Throwable cause) {
		super(message, cause);
	}
}
