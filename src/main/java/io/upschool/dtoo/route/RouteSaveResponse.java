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

	private Long departureAirportId;

	private Long destinationAirportId;
	
	

}
