package io.upschool.exception.route;

public class RouteNotFoundException extends RuntimeException {

	public RouteNotFoundException(String message) {
		super(message);
	}
}
