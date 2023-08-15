package io.upschool.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.upschool.dtoo.route.RouteSaveRequest;
import io.upschool.dtoo.route.RouteSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.exception.route.RouteAlreadySavedException;
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

		Airport departureByReference = airportService.getReferenceByCode(request.getDepartureAirportCode());
		Airport destinationByReference = airportService.getReferenceByCode(request.getDestinationAirportCode());

		// Checks if airport_id in request is exist in database. If does not exist
		// throws a not found exception.
		airportService.findAirportById(departureByReference.getId());
		airportService.findAirportById(destinationByReference.getId());

		Route newRoute = Route.builder().departureAirport(departureByReference)
				.destinationAirport(destinationByReference).build();

		Route savedRoute = routeRepository.save(newRoute);

		RouteSaveResponse response = RouteSaveResponse.builder().routeId(savedRoute.getId())
				.departureAirportName(savedRoute.getDepartureAirport().getName())
				.departureAirportLocation(savedRoute.getDepartureAirport().getLocation())
				.destinationAirportName(savedRoute.getDestinationAirport().getName())
				.destinationAirportLocation(savedRoute.getDestinationAirport().getLocation()).build();

		return response;
	}

	private void checkRouteIsAlreadySaved(RouteSaveRequest request) {
		Route route = routeRepository.findRouteByDepartureAirport_CodeAndDestinationAirport_Code(
				request.getDepartureAirportCode(), request.getDestinationAirportCode());
		System.out.println(route);
		if (route != null) {
			System.out.println(route);
			throw new RouteAlreadySavedException("This route already saved!");
		}
	}

	public List<RouteSaveResponse> getAllRoutes() {
		List<Route> routes = routeRepository.findAll();
		return routes.stream()
				.map(route -> new RouteSaveResponse(route.getId(), route.getDepartureAirport().getName(),
						route.getDepartureAirport().getLocation(), route.getDestinationAirport().getName(),
						route.getDestinationAirport().getLocation()))
				.collect(Collectors.toList());

	}

	public RouteSaveResponse findRouteById(Long id) {
		Route route = routeRepository.findById(id)
				.orElseThrow(() -> new RouteNotFoundException("Route could not found!"));
		return RouteSaveResponse.builder().routeId(route.getId())
				.departureAirportName(route.getDepartureAirport().getName())
				.departureAirportLocation(route.getDepartureAirport().getLocation())
				.destinationAirportName(route.getDestinationAirport().getName())
				.destinationAirportLocation(route.getDestinationAirport().getLocation()).build();
	}

	@Transactional
	public Route getReferenceById(Long id) {
		return routeRepository.getReferenceById(id);
	}

}
