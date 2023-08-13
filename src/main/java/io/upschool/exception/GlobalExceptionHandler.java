package io.upschool.exception;


import org.springframework.http.HttpHeaders;
import java.text.MessageFormat;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

		var response = BaseResponse.<Object>builder().status(HttpStatus.BAD_REQUEST.value())
				.isSuccess(false).build();

		return ResponseEntity.ok(response);
	}

	
	//general exception structure
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAll(final Exception exception, final WebRequest request) {
		System.out.println(
				"An error has occured. Exception:" + exception.getMessage() + request.getHeader("client-type"));
		var response = BaseResponse.<AirlineSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(exception.getMessage())
				.isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}
	
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errorBody=ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
                fieldError.getDefaultMessage()).toList();

        return new ResponseEntity<>(errorBody, status);
    }

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
	
	@ExceptionHandler(AirlineNotFoundException.class)
	public ResponseEntity<Object> handleAirlineNotFoundException(final Exception exception, final WebRequest request) {
		System.out.println(
				"An error has occured in Route. Exception:" + exception.getMessage() + request.getHeader("client-type"));
		var response = BaseResponse.<AirlineSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(exception.getMessage())
				.isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}
	
	
	
	@ExceptionHandler(AirportNotFoundException.class)
	public ResponseEntity<Object> handleAirportNotFoundException(final Exception exception, final WebRequest request) {
		System.out.println(
				"An error has occured in Route. Exception:" + exception.getMessage() + request.getHeader("client-type"));
		var response = BaseResponse.<AirportSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(exception.getMessage())
				.isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}
	
	
	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> handleFlightNotFoundException(final Exception exception, final WebRequest request) {
		System.out.println(
				"An error has occured in Route. Exception:" + exception.getMessage() + request.getHeader("client-type"));
		var response = BaseResponse.<AirportSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(exception.getMessage())
				.isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}
	
	
	@ExceptionHandler(RouteNotFoundException.class)
	public ResponseEntity<Object> handleRouteNotFoundException(final Exception exception, final WebRequest request) {
		System.out.println(
				"An error has occured in Route. Exception:" + exception.getMessage() + request.getHeader("client-type"));
		var response = BaseResponse.<RouteSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(exception.getMessage())
				.isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}
	
	
	@ExceptionHandler(TicketNotFountException.class)
	public ResponseEntity<Object> handleTicketNotFountException(final Exception exception, final WebRequest request) {
		System.out.println(
				"An error has occured in Route. Exception:" + exception.getMessage() + request.getHeader("client-type"));
		var response = BaseResponse.<TicketSaveResponse>builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(exception.getMessage())
				.isSuccess(false).build();
		return ResponseEntity.badRequest().body(response);
	}
	
	
////	@ExceptionHandler(MethodArgumentNotValidException.class)
//	@Override
//	public ResponseEntity<Object> handleMethodArgumentNotValid(final Exception exception, final WebRequest request){
//		System.out.println("An error has occured in Route. Exception:" + exception.getMessage() + request.getHeader("client-type"));
//		
//		var response = BaseResponse.<Object>builder().error(exception.getMessage()).build();
//		
//		return ResponseEntity.badRequest().body(response);
//		
//	}
//	
	

}
