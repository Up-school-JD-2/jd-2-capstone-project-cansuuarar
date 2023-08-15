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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
public class RouteController {

	private final RouteService routeService;

	@GetMapping
	public ResponseEntity<BaseResponse<Route>> getAllRoutes() {
		List<Route> allRoutes = routeService.getAllRoutes();

		BaseResponse<Route> response = BaseResponse.<Route>builder().status(HttpStatus.FOUND.value()).isSuccess(true)
				.listData(allRoutes).build();
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{routeId}")
	public ResponseEntity<Object> findRoute(@PathVariable("routeId") Long routeId) {
		Route route = routeService.findRouteById(routeId);
		BaseResponse<Route> response = BaseResponse.<Route>builder().status(HttpStatus.FOUND.value()).isSuccess(true)
				.data(route).build();
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Object> createRoute(@Valid @RequestBody RouteSaveRequest request) {
		RouteSaveResponse saveResponse = routeService.save(request);
		BaseResponse<RouteSaveResponse> response = BaseResponse.<RouteSaveResponse>builder()
				.status(HttpStatus.CREATED.value()).isSuccess(true).data(saveResponse).build();
		return ResponseEntity.ok(response);
	}

}
