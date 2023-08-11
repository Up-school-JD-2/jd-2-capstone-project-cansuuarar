package io.upschool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.upschool.dtoo.airport.AirportSaveRequest;
import io.upschool.dtoo.airport.AirportSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.exception.AirportAlreadySavedException;
import io.upschool.repository.AirportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirportService {

	private final AirportRepository airportRepository;

	@Transactional
	public AirportSaveResponse save(AirportSaveRequest request) {

		checkAirportIsAlreadySaved(request);

		Airport newAirport = Airport.builder().code(request.getCode()).name(request.getName()).build();

		Airport savedAirport = airportRepository.save(newAirport);

		AirportSaveResponse response = AirportSaveResponse.builder().id(savedAirport.getId())
				.code(savedAirport.getCode()).name(savedAirport.getName()).build();

		return response;

	}

	private void checkAirportIsAlreadySaved(AirportSaveRequest request) {
		int airportCountByCode = airportRepository.findAirportCountByAirportCode(request.getCode());
		if (airportCountByCode > 0) {
			throw new AirportAlreadySavedException("This airport already saved with that code!");
		}
	}

	public Airport save(Airport airport) {
		return airportRepository.save(airport);
	}

	public List<Airport> getAllAirports() {
		return airportRepository.findAll();
	}

	public Airport findAirportById(Long id) {
		return airportRepository.findById(id).orElse(null);
	}

	@Transactional
	public Airport getReferenceById(Long id) {
		return airportRepository.getReferenceById(id);
	}

	@Transactional
	public Airport getReferenceByNameLike(String name) {
		return airportRepository.findByNameLike(name);
	}
}
