package io.upschool.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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

		Route routeByReference = routeService.getReferenceById(request.getRouteId());
		Airline airlineByReference = airlineService.getReferenceById(request.getAirlineId());

		// Checks if route_id and airline_id in request is exist in database. If does
		// not exist throws a not found exception.
		routeService.findRouteById(request.getRouteId());
		airlineService.findAirlineById(request.getAirlineId());

		Flight newFlight = Flight.builder().departureDate(request.getDepartureDate())
				.arrivalDate(request.getArrivalDate()).totalSeat(request.getTotalSeat()).routeId(routeByReference)
				.airlineId(airlineByReference).flightNumber(generateFlightNumber()).build();

		var savedFlight = flightRepository.save(newFlight);

		FlightSaveResponse response = FlightSaveResponse.builder().id(savedFlight.getId())
				.flightNumber(savedFlight.getFlightNumber()).departureDate(savedFlight.getDepartureDate())
				.arrivalDate(savedFlight.getArrivalDate()).totalSeat(savedFlight.getTotalSeat())
				.routeId(savedFlight.getRouteId().getId()).airlineId(savedFlight.getAirlineId().getId()).build();
		return response;
	}

	public List<FlightSaveResponse> getAllFlight() {
		List<Flight> flights = flightRepository.findAll();
		return flights.stream()
				.map(flight -> new FlightSaveResponse(flight.getId(), flight.getFlightNumber(),
						flight.getDepartureDate(), flight.getArrivalDate(), flight.getTotalSeat(),
						flight.getRouteId().getId(), flight.getAirlineId().getId()))
				.collect(Collectors.toList());
	}

	public FlightSaveResponse findFlightById(Long id) {
		Flight flight = flightRepository.findById(id)
				.orElseThrow(() -> new FlightNotFoundException("Flight could not found!"));
		return FlightSaveResponse.builder().id(flight.getId()).flightNumber(flight.getFlightNumber())
				.departureDate(flight.getDepartureDate()).arrivalDate(flight.getArrivalDate())
				.totalSeat(flight.getTotalSeat()).routeId(flight.getRouteId().getId())
				.airlineId(flight.getAirlineId().getId()).build();
	}

	private String generateFlightNumber() {
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		String firstThreeDigit = uuidAsString.substring(0, 3);
		return "FLIGHT-" + firstThreeDigit;
	}

	@Transactional
	public Flight getReferenceById(Long id) {
		return flightRepository.getReferenceById(id);
	}

}
