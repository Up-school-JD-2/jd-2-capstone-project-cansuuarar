package io.upschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.upschool.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Long>{

	// TODO: Route sınıfı için airport code unu alacağın joinli bir query yaz.
}
