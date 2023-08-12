package io.upschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.upschool.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

	
//	int findRouteCountByDepartureAirportAndDestinationAirport(String departureAirportName,
//			String destinationAirportName);

	Route findRouteByDepartureAirportId_NameAndDestinationAirportId_Name(@Param("departureAirportName") String departureAirportName, @Param("destinationAirportName") String destinationAirportName);
	
	
}
