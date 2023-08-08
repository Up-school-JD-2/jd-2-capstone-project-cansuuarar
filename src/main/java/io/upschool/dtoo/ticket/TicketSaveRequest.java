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
public class TicketSaveRequest {

	private String passengerName;

	private String cardNumber;

	private Flight flight;

}
