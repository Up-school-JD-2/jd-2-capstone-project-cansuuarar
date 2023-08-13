package io.upschool.dtoo.flight;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightSavedRequest {

	@NotBlank(message = "Departure date cannot be blank!")
	@NotNull(message = "Departure date cannot be null!'")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
	private LocalDateTime departureDate;

	@NotBlank(message = "Arrival date cannot be blank!")
	@NotNull(message = "Arrival date cannot be null!'")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
	private LocalDateTime arrivalDate;

	@NotBlank(message = "Route id cannot be blank!")
	@NotNull(message = "Route id cannot be null!'")
	private Long routeId;

	private Double unitPrice;

	private String airlineCode;

}
