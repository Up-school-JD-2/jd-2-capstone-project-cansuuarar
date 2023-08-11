package io.upschool.service;

import java.util.List;

import org.springframework.stereotype.Service;
import io.upschool.dtoo.airline.AirlineSaveRequest;
import io.upschool.dtoo.airline.AirlineSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.repository.AirlineRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirlineService {

	private final AirlineRepository airlineRepository;

	@Transactional
	public AirlineSaveResponse save(AirlineSaveRequest request) {

		Airline newAirline = Airline.builder().airlineCode(request.getAirlineCode())
				.airlineName(request.getAirlineName()).build();

		Airline savedAirline = airlineRepository.save(newAirline);

		return AirlineSaveResponse.builder().airlineId(savedAirline.getId()).airlineCode(savedAirline.getAirlineCode())
				.airlineName(savedAirline.getAirlineName()).build();
	}

	public List<Airline> getAllAirlines() {
		return airlineRepository.findAll();

	}

	public Airline save(Airline airline) {
		return airlineRepository.save(airline);
	}

	public Airline findAirlineById(Long id) {
		return airlineRepository.findById(id).orElse(null);
	}

	@Transactional
	public Airline getReferenceById(Long id) {
		return airlineRepository.getReferenceById(id);
	}

	@Transactional
	public Airline getReferenceByCode(String code) {
		return airlineRepository.findByAirlineCode(code);
	}

//	public Airline searchFlight(Airline airline) {
//		
//		return airlineRepository.findByFlightId(airline.getFlightId());
//		
//		
//	}
}
