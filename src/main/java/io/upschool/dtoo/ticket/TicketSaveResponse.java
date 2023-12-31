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

	private Long ticketId;

	private String passengerName;

	private String cardNumber;

	private boolean isPurchased;

	private String ticketNumber;

	private String ticketPrice;

	private Long flightId;

	private String flightNumber;

	private String departureAirport;

	private String destinationAirport;

	private boolean isDeleted;

}
