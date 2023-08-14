package io.upschool.service;

import java.util.List;

import org.springframework.stereotype.Service;
import io.upschool.dtoo.airline.AirlineSaveRequest;
import io.upschool.dtoo.airline.AirlineSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.exception.airline.AirlineAlreadySavedException;
import io.upschool.exception.airline.AirlineNotFoundException;
import io.upschool.repository.AirlineRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirlineService {

	private final AirlineRepository airlineRepository;

	@Transactional
	public AirlineSaveResponse save(AirlineSaveRequest request) {

		checkIsAirlineAlreadySaved(request);

		Airline newAirline = Airline.builder().airlineCode(request.getAirlineCode())
				.airlineName(request.getAirlineName()).build();

		Airline savedAirline = airlineRepository.save(newAirline);

		return AirlineSaveResponse.builder().airlineId(savedAirline.getId()).airlineCode(savedAirline.getAirlineCode())
				.airlineName(savedAirline.getAirlineName()).build();
	}

	private void checkIsAirlineAlreadySaved(AirlineSaveRequest request) {
		int airlineCountByCode = airlineRepository.findAirlineCountByAirlineCode(request.getAirlineCode());
		if (airlineCountByCode > 0) {
			throw new AirlineAlreadySavedException("This airline already saved!");
		}
	}

	public List<Airline> getAllAirlines() {
		return airlineRepository.findAll();

	}

	public Airline findAirlineById(Long id) {
		return airlineRepository.findById(id).orElseThrow(() -> new AirlineNotFoundException("Airline could not found!"));
	}

	@Transactional
	public Airline getReferenceById(Long id) {
		return airlineRepository.getReferenceById(id);
	}

	@Transactional
	public Airline getReferenceByCode(String code) {
		return airlineRepository.findByAirlineCode(code);
	}

}
