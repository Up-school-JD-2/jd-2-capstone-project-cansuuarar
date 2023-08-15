package io.upschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.upschool.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

	public Airport findByCode(String code);
	
	@Query(value = "select count(a) from Airport a where a.code = :code")
	int findAirportCountByAirportCode(String code);
	
}
