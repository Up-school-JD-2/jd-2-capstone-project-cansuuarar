package io.upschool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.upschool.dtoo.route.RouteSaveRequest;
import io.upschool.dtoo.route.RouteSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.exception.RouteAlreadySavedException;
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

		checkRouteIsAlreadySaved(request);

		Airport departureByReference = airportService.getReferenceByNameLike(request.getDepartureAirportName());
		Airport destinationByReference = airportService.getReferenceByNameLike(request.getDestinationAirportName());

		Route newRoute = Route.builder().departureAirport(departureByReference)
				.destinationAirport(destinationByReference).build();

		Route savedRoute = routeRepository.save(newRoute);

		RouteSaveResponse response = RouteSaveResponse.builder().routeId(savedRoute.getId())
				.departureAirportName(savedRoute.getDepartureAirport().getName())
				.destinationAirportName(savedRoute.getDestinationAirport().getName()).build();

		return response;
	}

	private void checkRouteIsAlreadySaved(RouteSaveRequest request) {
		
		int routeCountByAir = routeRepository.findRouteCountByDepartureAirportAndDestinationAirport(
				request.getDepartureAirportName(), request.getDestinationAirportName());
		if (routeCountByAir > 0) {
			throw new RouteAlreadySavedException("This route already saved with that airports!");
		}
	}
	

	public List<Route> getAllRoutes() {
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
