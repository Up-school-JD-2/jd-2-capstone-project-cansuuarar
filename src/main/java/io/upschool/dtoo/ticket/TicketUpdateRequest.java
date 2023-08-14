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
public class TicketUpdateRequest {

	private Long id;

	@NotBlank(message = "Ticket number cannot be blank!")
	@NotNull(message = "Ticket number cannot be null!")
	private String ticketNumber;

}
