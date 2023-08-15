package io.upschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import io.upschool.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

	Route findRouteByDepartureAirport_CodeAndDestinationAirport_Code(
			@Param("departureAirportCode") String departureAirportCode,
			@Param("destinationAirportCode") String destinationAirportCode);
}
