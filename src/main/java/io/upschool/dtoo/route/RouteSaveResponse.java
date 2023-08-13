package io.upschool.dtoo.route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveResponse {

	private Long routeId;
	
	private String departureAirportName;
	
	private String departureAirportLocation;

	private String destinationAirportName;
	
	private String destinationAirportLocation;
	
}
