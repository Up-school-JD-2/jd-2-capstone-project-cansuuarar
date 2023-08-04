package io.upschool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.upschool.dtoo.flight.FlightSaveResponse;
import io.upschool.dtoo.flight.FlightSavedRequest;
import io.upschool.entity.Airline;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.repository.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightService {
	
	private final FlightRepository flightRepository;
	//private final AirlineService airlineService;
	private final RouteService routeService;
	
	
	@Transactional
	public FlightSaveResponse save(FlightSavedRequest request) {
		
		//Airline airlineByReference = airlineService.getReferenceById(request.getAirlineId());
		Route routeByReference =  routeService.getReferenceById(request.getRouteId());
		
		
		Flight newFlight = Flight.builder()
								 .departureDate(request.getDepartureDate())
								 .arrivalDate(request.getArrivalDate())
								 .routeId(routeByReference).build();
							//	 .airlineId(airlineByReference)
		
		
		var savedFlight = flightRepository.save(newFlight);
		
		
		FlightSaveResponse response = FlightSaveResponse.builder()
						   .departureDate(savedFlight.getDepartureDate())
						   .arrivalDate(savedFlight.getArrivalDate())
						   .routeId(savedFlight.getRouteId().getId())
						  // .airlineId(savedFlight.getAirlineId().getId())
						   .build();
						   

		return response;
		
	}
	
	
	public List<Flight> getAllFlight(){
		return flightRepository.findAll();
	}
	
	public Flight findFlightById(Long id) {
		return flightRepository.findById(id).orElse(null);
	}
	
	

}
