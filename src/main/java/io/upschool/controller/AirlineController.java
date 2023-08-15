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
import io.upschool.service.AirlineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/airlines")
@RequiredArgsConstructor
public class AirlineController {

	private final AirlineService airlineService;

	@GetMapping
	public ResponseEntity<Object> getAirlines() {
		List<AirlineSaveResponse> allAirlines = airlineService.getAllAirlines();
		BaseResponse<AirlineSaveResponse> response = BaseResponse.<AirlineSaveResponse>builder().status(HttpStatus.FOUND.value())
				.isSuccess(true).listData(allAirlines).build();
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{airlineId}")
	public ResponseEntity<Object> findAirline(@PathVariable("airlineId") Long airlineId) {
		AirlineSaveResponse airlineResponse = airlineService.findAirlineById(airlineId);
		BaseResponse<AirlineSaveResponse> response = BaseResponse.<AirlineSaveResponse>builder().status(HttpStatus.FOUND.value())
				.isSuccess(true).data(airlineResponse).build();
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Object> createAirline(@Valid @RequestBody AirlineSaveRequest airlineSaveRequest) {
		AirlineSaveResponse saveResponse = airlineService.save(airlineSaveRequest);
		BaseResponse<AirlineSaveResponse> response = BaseResponse.<AirlineSaveResponse>builder()
				.status(HttpStatus.CREATED.value()).isSuccess(true).data(saveResponse).build();
		return ResponseEntity.ok(response);
	}

}
