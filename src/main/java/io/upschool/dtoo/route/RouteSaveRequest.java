package io.upschool.dtoo.route;

import io.upschool.entity.Airport;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveRequest {

	private String departureAirportName;

	private String destinationAirportName;

}
