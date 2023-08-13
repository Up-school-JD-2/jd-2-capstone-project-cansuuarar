package io.upschool.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.upschool.constants.DomainConstants;
import io.upschool.dtoo.flight.FlightSaveResponse;
import io.upschool.dtoo.flight.FlightSavedRequest;
import io.upschool.entity.Airline;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.exception.flight.FlightNotFoundException;
import io.upschool.repository.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightService {
	
	private final FlightRepository flightRepository;
	private final AirlineService airlineService;
	private final RouteService routeService;
	
	
	
	@Transactional
	public FlightSaveResponse save(FlightSavedRequest request) {
		
		Route routeByReference =  routeService.getReferenceById(request.getRouteId());
		Airline airlineByReference = airlineService.getReferenceByCode(request.getAirlineCode());
		
		Flight newFlight = Flight.builder()
								 .departureDate(request.getDepartureDate())
								 .arrivalDate(request.getArrivalDate())
								 .routeId(routeByReference)
								 .unitPrice(request.getUnitPrice())
								 .airlineId(airlineByReference)
								 .flightNumber(generateFlightNumber())
								 .build();
		
		newFlight.setTotalSeat(DomainConstants.TOTAL_SEAT_NUMBER);
		
		var savedFlight = flightRepository.save(newFlight);
		
		
		FlightSaveResponse response = FlightSaveResponse.builder()
						   .id(savedFlight.getId())
						   .flightNumber(savedFlight.getFlightNumber())
						   .departureDate(savedFlight.getDepartureDate())
						   .arrivalDate(savedFlight.getArrivalDate())
						   .totalSeat(savedFlight.getTotalSeat())
						   .unitPrice(savedFlight.getUnitPrice())
						   .routeId(savedFlight.getRouteId().getId())
						   .airlineCode(savedFlight.getAirlineId().getAirlineCode())
						   .build();			   

		return response;
	}
	
	
	public List<Flight> getAllFlight(){
		return flightRepository.findAll();
	}
	
	public Flight findFlightById(Long id) {
		return flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException("Flight could not found!"));
	}
	
	private String generateFlightNumber() {

		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		String firstThreeDigit = uuidAsString.substring(0, 3);
		return "FLIGHT-" + firstThreeDigit;

	}
	
	@Transactional
	public Flight getReferenceById(Long id) {
		
		return  flightRepository.getReferenceById(id);
		
	}
	
	public void checkFlightExist(Long flightId) {
		
		flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException("Flight could not found!"));
	}
	

}
