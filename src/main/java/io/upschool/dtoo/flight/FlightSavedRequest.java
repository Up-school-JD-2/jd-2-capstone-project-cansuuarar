package io.upschool.dtoo.flight;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSavedRequest {

	private LocalDateTime departureDate;

	private LocalDateTime arrivalDate;
	
	private int totalSeat;

	private Long routeId;

	private Long airlineId;
	
}
