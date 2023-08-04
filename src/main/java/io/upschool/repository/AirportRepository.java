package io.upschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.upschool.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

}
