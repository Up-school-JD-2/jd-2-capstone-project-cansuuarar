package io.upschool.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.upschool.dtoo.BaseResponse;
import io.upschool.dtoo.flight.FlightSaveResponse;
import io.upschool.dtoo.flight.FlightSavedRequest;
import io.upschool.entity.Flight;
import io.upschool.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

	private final FlightService flightService;

	@GetMapping
	public ResponseEntity<BaseResponse<Flight>> getAllFlight() {
		List<Flight> allFlight = flightService.getAllFlight();
		BaseResponse<Flight> response = BaseResponse.<Flight>builder().status(HttpStatus.FOUND.value()).isSuccess(true)
				.listData(allFlight).build();
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{flightId}")
	public ResponseEntity<Object> findFlight(@PathVariable("flightId") Long flightId) {
		Flight flight = flightService.findFlightById(flightId);
		BaseResponse<Flight> response = BaseResponse.<Flight>builder().status(HttpStatus.FOUND.value()).isSuccess(true)
				.data(flight).build();
		return ResponseEntity.ok(response);

	}

	@PostMapping
	public ResponseEntity<Object> createFlight(@Valid @RequestBody FlightSavedRequest request) {
		FlightSaveResponse saveResponse = flightService.save(request);
		BaseResponse<FlightSaveResponse> response = BaseResponse.<FlightSaveResponse>builder()
				.status(HttpStatus.CREATED.value()).isSuccess(true).data(saveResponse).build();
		return ResponseEntity.ok(response);
	}

}
