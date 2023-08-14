package io.upschool.dtoo.ticket;


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
public class TicketSaveRequest {

	@NotBlank(message = "Passenger Name cannot be blank!")
	@NotNull(message = "Passenger Name cannot be null!'")
	private String passengerName;

	@NotBlank(message = "Credit card number cannot be blank!")
	@NotNull(message = "Credit card number cannot be null!'")
	private String cardNumber;
	
	@NotNull(message = "Flight id cannot be null!'")
	private Long flightId;
	

}
