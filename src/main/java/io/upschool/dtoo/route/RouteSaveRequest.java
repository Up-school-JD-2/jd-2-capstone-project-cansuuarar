package io.upschool.dtoo.route;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveRequest {

	@NotBlank(message = "Departure airport code cannot be blank!")
	@NotNull(message = "Departure airport code cannot be null!'")
	private String departureAirportCode;

	@NotBlank(message = "Destination airport code cannot be blank!")
	@NotNull(message = "Destination airport code cannot be null!'")
	private String destinationAirportCode;

}
