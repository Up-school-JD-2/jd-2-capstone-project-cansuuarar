package io.upschool.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.upschool.dtoo.route.RouteSaveRequest;
import io.upschool.dtoo.route.RouteSaveResponse;
import io.upschool.entity.Route;
import io.upschool.service.RouteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

	private final RouteService routeService;

	@GetMapping
	public ResponseEntity<List<Route>> getAllRoutes() {
		var allRoutes = routeService.getAllRoutes();
		return ResponseEntity.ok(allRoutes);
	}

	@GetMapping(path = "/{routeId}")
	public ResponseEntity<Route> findRoute(@PathVariable("routeId") Long routeId) {
		return ResponseEntity.ok(routeService.findRouteById(routeId));

	}

	@PostMapping
	public ResponseEntity<RouteSaveResponse> createRoute(@RequestBody RouteSaveRequest request) {
		var response = routeService.save(request);
		return ResponseEntity.ok(response);
	}

}
