package io.upschool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.upschool.dtoo.route.RouteSaveRequest;
import io.upschool.dtoo.route.RouteSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.exception.RouteAlreadySavedException;
import io.upschool.exception.route.RouteNotFoundException;
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

		Route newRoute = Route.builder().departureAirportId(departureByReference)
				.destinationAirportId(destinationByReference).build();

		Route savedRoute = routeRepository.save(newRoute);

		RouteSaveResponse response = RouteSaveResponse.builder().routeId(savedRoute.getId())
				.departureAirportName(savedRoute.getDepartureAirportId().getName())
				.destinationAirportName(savedRoute.getDestinationAirportId().getName()).build();

		return response;
	}

	private void checkRouteIsAlreadySaved(RouteSaveRequest request) {
//		
//		int routeCountByAir = routeRepository.findRouteCountByDepartureAirportAndDestinationAirport(
//				request.getDepartureAirportName(), request.getDestinationAirportName());
//		if (routeCountByAir > 0) {
//			throw new RouteAlreadySavedException("This route already saved with that airports!");
//		}
		
		
		Route route = routeRepository.findRouteByDepartureAirportId_NameAndDestinationAirportId_Name(
				request.getDepartureAirportName(), request.getDestinationAirportName());
		System.out.println(route);
		if (route != null) {
			System.out.println(route);
			throw new RouteAlreadySavedException("This route already saved with that airports!");
		}
		
		
	}
	

	public List<Route> getAllRoutes() {
		return routeRepository.findAll();
	}

	public Route findRouteById(Long id) {
		return routeRepository.findById(id).orElseThrow(() -> new RouteNotFoundException("Route could not found!"));
	}

	@Transactional
	public Route getReferenceById(Long id) {
		return routeRepository.getReferenceById(id);
	}
}
