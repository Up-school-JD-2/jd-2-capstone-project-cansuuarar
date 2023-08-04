package io.upschool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.upschool.dtoo.route.RouteSaveRequest;
import io.upschool.dtoo.route.RouteSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.repository.RouteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RouteService {

	private final RouteRepository routeRepository;

	private final AirportService airportService;

	@Transactional
	public RouteSaveResponse save(RouteSaveRequest request) {
		Airport departureByReference = airportService.getReferenceById(request.getDepartureAirportId());
		Airport destinationByReference = airportService.getReferenceById(request.getDestinationAirportId());
		
		

		Route newRoute = Route
						.builder()
						.departureAirport(departureByReference)
						.destinationAirport(destinationByReference)
						.build();

		Route savedRoute = routeRepository.save(newRoute);

		RouteSaveResponse response = RouteSaveResponse
									 .builder().routeInfo(savedRoute.getDepartureAirport().getCode() + " -> " + savedRoute.getDestinationAirport().getCode())
									 .build();
		
		return response;
	}

	
	
	public List<Route> getAllRoutes(){
		return routeRepository.findAll();
	}
	
	public Route findRouteById(Long id) {
		return routeRepository.findById(id).orElse(null);
	}
	
	
	
	@Transactional
	public Route getReferenceById(Long id) {
		return routeRepository.getReferenceById(id);
	}
}
 