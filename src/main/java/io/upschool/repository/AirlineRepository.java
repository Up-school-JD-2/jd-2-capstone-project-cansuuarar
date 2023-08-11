package io.upschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.upschool.entity.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
	
	public Airline findByAirlineCode(String airlineCode);
	
	
	@Query(value = "select count(a) from Airline a where a.airlineCode = :airlineCode")
	int findAirlineCountByAirlineCode(String airlineCode);
	

}
