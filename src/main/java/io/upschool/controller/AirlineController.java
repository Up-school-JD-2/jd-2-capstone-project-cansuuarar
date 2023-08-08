package io.upschool.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.upschool.dtoo.BaseResponse;
import io.upschool.dtoo.airline.AirlineSaveRequest;
import io.upschool.dtoo.airline.AirlineSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.service.AirlineService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/airlines")
@RequiredArgsConstructor
public class AirlineController {

	private final AirlineService airlineService;

	@GetMapping
	public ResponseEntity<List<Airline>> getAirlines() {
		var airlines = airlineService.getAllAirlines();
		return ResponseEntity.ok(airlines);
	}

	@GetMapping(path = "/{airlineId}")
	public ResponseEntity<Airline> findAirline(@PathVariable("airlineId") Long airlineId) {
		return ResponseEntity.ok(airlineService.findAirlineById(airlineId));

	}

	@PostMapping
	public ResponseEntity<Object> createAirline(@RequestBody AirlineSaveRequest airlineSaveRequest) {
		var airlineSaveResponse = airlineService.save(airlineSaveRequest);
		var response = BaseResponse.<AirlineSaveResponse>builder()
						.status(HttpStatus.CREATED.value()).isSuccess(true)
						.data(airlineSaveResponse).build();

		return ResponseEntity.ok(response);
	}

}
