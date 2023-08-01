package io.upschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.upschool.entity.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {

}
