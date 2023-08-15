package io.upschool.exception;

import org.springframework.http.HttpHeaders;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
import io.upschool.dtoo.ticket.TicketSaveResponse;
import io.upschool.exception.airline.AirlineAlreadySavedException;
import io.upschool.exception.airline.AirlineNotFoundException;
import io.upschool.exception.airport.AirportAlreadySavedException;
import io.upschool.exception.airport.AirportNotFoundException;
import io.upschool.exception.flight.FlightAlreadySavedException;
import io.upschool.exception.flight.FlightNotFoundException;
import io.upschool.exception.route.RouteAlreadySavedException;
import io.upschool.exception.route.RouteNotFoundException;
import io.upschool.exception.ticket.TicketNotFountException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {

		final var errorMessage = MessageFormat.format("No handler found for {0} {1}", ex.getHttpMethod(),
				ex.getRequestURL());
		System.out.println(errorMessage);
		BaseResponse<Object> response = BaseResponse.<Object>builder().status(HttpStatus.BAD_REQUEST.value())
				.isSuccess(false).build();
		return ResponseEntity.ok(response);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AirlineAlreadySavedException.class)
	public ResponseEntity<Object> handleAirlineAlreadySavedException(AirlineAlreadySavedException exception) {
		System.out.println("An error has occured in airline save operation. Exception:" + exception.getMessage());
		BaseResponse<AirlineSaveResponse> response = BaseResponse.<AirlineSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value()).error(exception.getMessage()).isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(AirportAlreadySavedException.class)
	public ResponseEntity<Object> handleAirportAlreadySavedException(AirportAlreadySavedException exception) {
		System.out.println("An error has occured in airport save operation. Exception:" + exception.getMessage());
		BaseResponse<AirportSaveResponse> response = BaseResponse.<AirportSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value()).error(exception.getMessage()).isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(FlightAlreadySavedException.class)
	public ResponseEntity<Object> handleFlightAlreadySavedException(FlightAlreadySavedException exception) {
		System.out.println("An error has occured in flight save operation. Exception:" + exception.getMessage());
		BaseResponse<FlightSaveResponse> response = BaseResponse.<FlightSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value()).error(exception.getMessage()).isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(RouteAlreadySavedException.class)
	public ResponseEntity<Object> handleRouteAlreadySavedException(RouteAlreadySavedException exception) {
		System.out.println("An error has occured in route save operation. Exception:" + exception.getMessage());
		BaseResponse<RouteSaveResponse> response = BaseResponse.<RouteSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value()).error(exception.getMessage()).isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(AirlineNotFoundException.class)
	public ResponseEntity<Object> handleAirlineNotFoundException(AirlineNotFoundException exception) {
		System.out.println("Airline could not found in database. Exception:" + exception.getMessage());
		BaseResponse<AirlineSaveResponse> response = BaseResponse.<AirlineSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value()).error(exception.getMessage()).isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(AirportNotFoundException.class)
	public ResponseEntity<Object> handleAirportNotFoundException(AirportNotFoundException exception) {
		System.out.println("Airport could not found in database. Exception: " + exception.getMessage());
		BaseResponse<AirportSaveResponse> response = BaseResponse.<AirportSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value()).error(exception.getMessage()).isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> handleFlightNotFoundException(FlightNotFoundException exception) {
		System.out.println("Flight could not found in database. Exception:" + exception.getMessage());
		BaseResponse<FlightSaveResponse> response = BaseResponse.<FlightSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value()).error(exception.getMessage()).isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(RouteNotFoundException.class)
	public ResponseEntity<Object> handleRouteNotFoundException(final Exception exception) {
		System.out.println("Route could not found in database. Exception:" + exception.getMessage());
		BaseResponse<RouteSaveResponse> response = BaseResponse.<RouteSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value()).error(exception.getMessage()).isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(TicketNotFountException.class)
	public ResponseEntity<Object> handleTicketNotFountException(final Exception exception, final WebRequest request) {
		System.out.println("An error has occured in Route. Exception:" + exception.getMessage()
				+ request.getHeader("client-type"));
		BaseResponse<TicketSaveResponse> response = BaseResponse.<TicketSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value()).error(exception.getMessage()).isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAll(final Exception exception, final WebRequest request) {
		System.out.println(
				"An error has occured. Exception:" + exception.getMessage() + request.getHeader("client-type"));
		var response = BaseResponse.<Object>builder().status(HttpStatus.BAD_REQUEST.value())
				.error(exception.getMessage()).isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}

}
