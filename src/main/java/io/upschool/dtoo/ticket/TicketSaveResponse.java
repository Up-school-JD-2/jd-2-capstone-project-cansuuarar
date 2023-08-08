package io.upschool.dtoo.ticket;

import io.upschool.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveResponse {

	private Long ticketNumber;

	private String passengerName;

	private boolean isPurchased;

	private Double seatNumber;
	
	private Flight flight;

}
