package io.upschool.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.upschool.dtoo.BaseResponse;
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
	public ResponseEntity<Object> createRoute(@RequestBody RouteSaveRequest request) {
		var routeSaveResponse = routeService.save(request);
		var response  = BaseResponse.<RouteSaveResponse>builder()
				.status(HttpStatus.CREATED.value())
				.isSuccess(true)
				.data(routeSaveResponse)
				.build();
		
		return ResponseEntity.ok(response);
	}

}
