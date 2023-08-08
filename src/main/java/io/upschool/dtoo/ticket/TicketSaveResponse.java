package io.upschool.dtoo.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveResponse {

	private String passengerName;
	
	private String cardNumber;
	
	private boolean isPurchased;
	
	private String ticketNumber;

	private Long seatNumber;
	
	private Long flightId;

}
