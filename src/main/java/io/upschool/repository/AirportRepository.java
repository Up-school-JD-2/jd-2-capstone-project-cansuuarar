package io.upschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.upschool.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

	@Query(value = "select * from airports a where a.airport_name like %:name%", nativeQuery = true)
	public Airport findByNameLike(@Param("name") String name);

}
