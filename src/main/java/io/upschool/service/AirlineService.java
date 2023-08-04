package io.upschool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.upschool.dtoo.airline.AirlineSaveRequest;
import io.upschool.dtoo.airline.AirlineSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.repository.AirlineRepository;
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
							//.airplane(airlineSaveRequest.getAirplane())
							.build();
		
		Airline savedAirline = airlineRepository.save(newAirline);
		
		return AirlineSaveResponse
				.builder()
				.codeId(savedAirline.getAirlineCode() + "_" + savedAirline.getId())
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
}
 