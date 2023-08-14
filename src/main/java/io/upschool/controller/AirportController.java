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
import io.upschool.dtoo.airport.AirportSaveRequest;
import io.upschool.dtoo.airport.AirportSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.service.AirportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {

	private final AirportService airportService;

	@GetMapping
	public ResponseEntity<List<Airport>> getAllAirports() {
		var allAirports = airportService.getAllAirports();
		return ResponseEntity.ok(allAirports);
	}

	@GetMapping(path = "/{airportId}")
	public ResponseEntity<Airport> findAirport(@PathVariable("airportId") Long airportId) {
		return ResponseEntity.ok(airportService.findAirportById(airportId));
	}

	@PostMapping
	public ResponseEntity<Object> createAirline(@Valid @RequestBody AirportSaveRequest airportSaveRequest) {
		var airportSaveResponse = airportService.save(airportSaveRequest);
		var response = BaseResponse.<AirportSaveResponse>builder().status(HttpStatus.CREATED.value()).isSuccess(true)
				.data(airportSaveResponse).build();
		return ResponseEntity.ok(response);
	}

}
