package io.upschool.exception.airport;

public class AirportAlreadySavedException extends RuntimeException{
	
	public AirportAlreadySavedException(String message) {
		super(message);
	}

	public AirportAlreadySavedException(String message, Throwable cause) {
		super(message, cause);
	}

}
