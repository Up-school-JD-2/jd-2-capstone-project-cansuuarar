package io.upschool.dtoo.flight;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSavedRequest {

	@Future(message = "Departure date must be a future date!")
	@NotNull(message = "Departure date cannot be null!")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
	private LocalDateTime departureDate;

	@Future(message = "Arrival date must be a future date")
	@NotNull(message = "Arrival date cannot be null!")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
	private LocalDateTime arrivalDate;

	private int totalSeat;

	@NotNull(message = "Route id cannot be null!")
	private Long routeId;

	@NotNull(message = "Airline id cannot be null!")
	private Long airlineId;

}
