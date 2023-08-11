package io.upschool.dtoo.flight;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSavedRequest {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape=JsonFormat.Shape.STRING)
	private LocalDateTime departureDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape=JsonFormat.Shape.STRING)
	private LocalDateTime arrivalDate;

	private Long routeId;

	private String airlineCode;

}
