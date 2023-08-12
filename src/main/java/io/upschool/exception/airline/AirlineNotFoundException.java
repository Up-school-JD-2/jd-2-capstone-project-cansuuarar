package io.upschool.exception.airline;

public class AirlineNotFoundException extends RuntimeException{

	public AirlineNotFoundException(String message) {
		super(message);
	}
}
