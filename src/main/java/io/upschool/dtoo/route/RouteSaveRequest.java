package io.upschool.dtoo.route;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveRequest {

	private String departureAirportCode;

	private String destinationAirportCode;

}
