package io.upschool.dtoo.airline;

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
public class AirlineSaveRequest {

	@NotBlank(message = "Airline code cannot be blank!")
	@NotNull(message = "Airline code cannot be null!")
	private String airlineCode;

	@NotBlank(message = "Airline name cannot be blank!")
	@NotNull(message = "Airline name cannot be null!")
	private String airlineName;

}
