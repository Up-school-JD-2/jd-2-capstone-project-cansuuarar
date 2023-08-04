package io.upschool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.upschool.dtoo.airline.AirlineSaveRequest;
import io.upschool.dtoo.airline.AirlineSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.entity.Flight;
import io.upschool.repository.AirlineRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirlineService {

	private final AirlineRepository airlineRepository;

	public AirlineSaveResponse save(AirlineSaveRequest airlineSaveRequest) {
		
		Airline newAirline = Airline
							.builder()
							.airlineCode(airlineSaveRequest.getAirlineCode())
							.airlineName(airlineSaveRequest.getAirlineName())
							.airplane(airlineSaveRequest.getAirplane())
							.build();
		
		Airline savedAirline = airlineRepository.save(newAirline);
		
		return AirlineSaveResponse
				.builder()
				.airlineCode(savedAirline.getAirlineCode())
				.airlineName(savedAirline.getAirlineName())
				.airplane(savedAirline.getAirplane())
				.build();
	}
	
	
	public List<Airline> getAllAirlines(){
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
	
	
	public Airline addFlight(Flight flight) {
		return null;
		
	
		
	}
}



















 