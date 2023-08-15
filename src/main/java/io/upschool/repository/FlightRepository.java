package io.upschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.upschool.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>{
	
}
