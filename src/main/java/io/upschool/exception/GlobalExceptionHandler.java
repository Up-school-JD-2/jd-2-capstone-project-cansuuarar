package io.upschool.exception;

import java.net.http.HttpHeaders;
import java.text.MessageFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.upschool.dtoo.BaseResponse;
import io.upschool.dtoo.airline.AirlineSaveResponse;
import io.upschool.dtoo.airport.AirportSaveResponse;
import io.upschool.dtoo.flight.FlightSaveResponse;
import io.upschool.dtoo.route.RouteSaveResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {

		final var errorMessage = MessageFormat.format("No handler found for {0} {1}", ex.getHttpMethod(),
				ex.getRequestURL());

		System.out.println(errorMessage);

		var response = BaseResponse.<AirlineSaveResponse>builder().status(HttpStatus.BAD_REQUEST.value())
				.isSuccess(false).build();

		return ResponseEntity.ok(response);
	}

	//general exception structure
	/*
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAll(final Exception exception, final WebRequest request) {
		System.out.println(
				"An error has occured. Exception:" + exception.getMessage() + request.getHeader("client-type"));
		// return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		var response = BaseResponse.<AirlineSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(exception.getMessage())
				.isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}
	*/
	
	@ExceptionHandler(AirlineAlreadySavedException.class)
	public ResponseEntity<Object> handleAirlineAlreadySavedException(final Exception exception, final WebRequest request) {
		System.out.println(
				"An error has occured in airline. Exception:" + exception.getMessage() + request.getHeader("client-type"));
		var response = BaseResponse.<AirlineSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(exception.getMessage())
				.isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}
	
	
	@ExceptionHandler(AirportAlreadySavedException.class)
	public ResponseEntity<Object> handleAirportAlreadySavedException(final Exception exception, final WebRequest request) {
		System.out.println(
				"An error has occured in airport. Exception:" + exception.getMessage() + request.getHeader("client-type"));
		var response = BaseResponse.<AirportSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(exception.getMessage())
				.isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}
	
	@ExceptionHandler(FlightAlreadySavedException.class)
	public ResponseEntity<Object> handleFlightAlreadySavedException(final Exception exception, final WebRequest request) {
		System.out.println(
				"An error has occured in flight. Exception:" + exception.getMessage() + request.getHeader("client-type"));
		var response = BaseResponse.<FlightSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(exception.getMessage())
				.isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}
	
	
	@ExceptionHandler(RouteAlreadySavedException.class)
	public ResponseEntity<Object> handleRouteAlreadySavedException(final Exception exception, final WebRequest request) {
		System.out.println(
				"An error has occured in Route. Exception:" + exception.getMessage() + request.getHeader("client-type"));
		var response = BaseResponse.<RouteSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(exception.getMessage())
				.isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}
	
	

}
