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
	public ResponseEntity<BaseResponse<Airport>> getAllAirports() {
		List<Airport> allAirports = airportService.getAllAirports();
		BaseResponse<Airport> response = BaseResponse.<Airport>builder().status(HttpStatus.FOUND.value())
				.isSuccess(true).listData(allAirports).build();
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{airportId}")
	public ResponseEntity<Object> findAirport(@PathVariable("airportId") Long airportId) {
		Airport airport = airportService.findAirportById(airportId);
		BaseResponse<Airport> response = BaseResponse.<Airport>builder().status(HttpStatus.FOUND.value())
				.isSuccess(true).data(airport).build();
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Object> createAirline(@Valid @RequestBody AirportSaveRequest airportSaveRequest) {
		AirportSaveResponse saveResponse = airportService.save(airportSaveRequest);
		BaseResponse<AirportSaveResponse> response = BaseResponse.<AirportSaveResponse>builder()
				.status(HttpStatus.CREATED.value()).isSuccess(true).data(saveResponse).build();
		return ResponseEntity.ok(response);
	}

}
