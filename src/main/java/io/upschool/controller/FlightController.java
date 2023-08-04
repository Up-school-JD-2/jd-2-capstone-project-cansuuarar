package io.upschool.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.upschool.dtoo.flight.FlightSaveResponse;
import io.upschool.dtoo.flight.FlightSavedRequest;
import io.upschool.entity.Flight;
import io.upschool.service.FlightService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

	private final FlightService flightService;

	@GetMapping
	public ResponseEntity<List<Flight>> getAllFlight() {
		var flights = flightService.getAllFlight();
		return ResponseEntity.ok(flights);
	}

	@GetMapping(path = "/{flightId}")
	public ResponseEntity<Flight> findFlight(@PathVariable("flightId") Long flightId) {
		return ResponseEntity.ok(flightService.findFlightById(flightId));
	}

	
	@PostMapping
	public ResponseEntity<FlightSaveResponse> createFlight(@RequestBody FlightSavedRequest request) {
		var response = flightService.save(request);
		return ResponseEntity.ok(response);
	}
	
	
}
