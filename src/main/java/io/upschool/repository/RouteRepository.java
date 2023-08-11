package io.upschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.upschool.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Long>{

	@Query(value = "select count(r) from Route r where r.departureAirport=:departureAirport and r.destinationAirport = :destinationAirport")
	int findRouteCountByDepartureAirportAndDestinationAirport(String departureAirport, String destinationAirport);
	
	
}
