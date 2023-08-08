package io.upschool.service;

import java.util.Random;

import org.springframework.stereotype.Service;
import io.upschool.dtoo.ticket.TicketSaveRequest;
import io.upschool.dtoo.ticket.TicketSaveResponse;
import io.upschool.entity.Ticket;
import io.upschool.repository.TicketRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {

	private final TicketRepository ticketRepository;
	private final FlightService flightService;
	Random random = new Random();
	

	
	public TicketSaveResponse purchaseTicket(TicketSaveRequest request) {

		Ticket ticket = Ticket.builder()
				.passengerName(request.getPassengerName())
				.cardNumber(maskCreditCard(request.getCardNumber()))
				.flight(request.getFlight()).build();

		Ticket savedTicket = ticketRepository.save(ticket);
		
		
		TicketSaveResponse.builder()
						  .passengerName(savedTicket.getPassengerName())
						  .ticketNumber(createTicketNumber())
						  .isPurchased(true)
						  .build();
						
						  

		return null;

	}

	private String maskCreditCard(String creditCardNumber) {

		int lastVisibleDigit = 4;

		StringBuilder maskedCard = new StringBuilder();

		for (int i = 0; i < creditCardNumber.length(); i++) {
			if (i < creditCardNumber.length() - lastVisibleDigit) {
				maskedCard.append("*");
			} else {
				maskedCard.append(creditCardNumber.charAt(i));
			}
		}

		return maskedCard.toString();

	}
	
	private Long createTicketNumber() {
		
		Long randomNumber = random.nextLong(1000);
		
		
		return randomNumber ;
		
	} 
	
	private int generateSeatNumber() {
		
		int seatNumber = 0; 
		Ticket ticket = Ticket.builder().seatNumber(seatNumber).build();
		ticketRepository.save(ticket);
		
		/*
		 * flight dan gelen total seat den az olacak şekilde genratae random seat number
		 * 
		 * */
		
		int totalSeatNumber = 10;
		 for(int i= 0; i< totalSeatNumber ; i++) {
			seatNumber = random.nextInt(totalSeatNumber);
		 }
		
		return seatNumber;	
		
	}
	
	

}
