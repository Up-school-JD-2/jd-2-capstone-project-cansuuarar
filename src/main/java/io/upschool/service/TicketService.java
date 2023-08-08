package io.upschool.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import io.upschool.dtoo.ticket.TicketSaveRequest;
import io.upschool.dtoo.ticket.TicketSaveResponse;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
import io.upschool.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {

	private final TicketRepository ticketRepository;
	private final FlightService flightService;
	Random random = new Random();
	

	@Transactional
	public TicketSaveResponse purchaseTicket(TicketSaveRequest request) {
		Flight flightReferenceById = flightService.getReferenceById(request.getFlightId());

		Ticket ticket = Ticket.builder()
				.passengerName(request.getPassengerName())
				.cardNumber(maskCreditCard(request.getCardNumber()))
				.seatNumber(request.getTotalSeat() - random.nextLong(request.getTotalSeat()))
				.flightId(flightReferenceById)				
				.build();

		Ticket savedTicket = ticketRepository.save(ticket);
		
		
		TicketSaveResponse response = TicketSaveResponse.builder()
						  .passengerName(savedTicket.getPassengerName())
						  .cardNumber(savedTicket.getCardNumber())
						  .isPurchased(true)
						  .ticketNumber(createTicketNumber())
						  .seatNumber(savedTicket.getSeatNumber())						  
						  .build();
		return response;

	}

//	private String maskCreditCard(String creditCardNumber) {
//
//		int lastVisibleDigit = 4;
//
//		StringBuilder maskedCard = new StringBuilder();
//
//		for (int i = 0; i < creditCardNumber.length(); i++) {
//			if (i < creditCardNumber.length() - lastVisibleDigit) {
//				
//				maskedCard.append("*");
//			} else {
//				maskedCard.append(creditCardNumber.charAt(i));
//			}
//		}
//
//		String string1 = maskedCard.toString();
//		
//		return string1.replaceAll( "[^\\d]", "" );
//
//	}
	
	public String maskCreditCard(String creditCardNumber) {
		
		String[] cardNumberArray = creditCardNumber.split("\\D+");
		//1234, ***
		//except last four digit masking
		StringBuilder maskedCardNumber = new StringBuilder();
		
		for(int i = 0; i<cardNumberArray.length -1 ; i++) {
			String piece = cardNumberArray[i];
			if(!piece.isEmpty()) {
				for(int j = 0; j<piece.length();j++) {
					maskedCardNumber.append('*');
				}
				maskedCardNumber.append('-');
			}
			
		}
		
		maskedCardNumber.append(creditCardNumber.substring(creditCardNumber.length() - 4));
		return maskedCardNumber.toString();
		
	}
	
	
	private String createTicketNumber() {
		int number = random.nextInt(1000);
		System.out.println("TICKET-" + number);
		return "TICKET" + number;
	} 
	
	
	public List<Ticket> getAllTicket(){
		return ticketRepository.findAll();
	}
	
	
	public Ticket findTicketById(Long id) {
		return ticketRepository.findById(id).orElse(null);
	}
	
	
	
	

}
