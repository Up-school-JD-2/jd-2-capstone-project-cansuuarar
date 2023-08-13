package io.upschool.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import io.upschool.constants.DomainConstants;
import io.upschool.dtoo.ticket.TicketSaveRequest;
import io.upschool.dtoo.ticket.TicketSaveResponse;
import io.upschool.dtoo.ticket.TicketUpdateRequest;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
import io.upschool.exception.ticket.TicketNotFountException;
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

		flightService.checkFlightExist(request.getFlightId());

		Ticket ticket = Ticket.builder().passengerName(request.getPassengerName())
				.cardNumber(maskCreditCard(request.getCardNumber())).flightId(flightReferenceById)
				.ticketNumber(generateTicketNumber())
				.ticketPrice(flightReferenceById.getUnitPrice() + " TL")
				.isPurchased(true).build();

		Ticket savedTicket = ticketRepository.save(ticket);

		TicketSaveResponse response = TicketSaveResponse.builder().ticketId(savedTicket.getId())
				.passengerName(savedTicket.getPassengerName()).cardNumber(savedTicket.getCardNumber()).isPurchased(true)
				.ticketNumber(savedTicket.getTicketNumber()).ticketPrice(savedTicket.getTicketPrice())
				.flightId(savedTicket.getFlightId().getId())
				.flightNumber(savedTicket.getFlightId().getFlightNumber())
				.departureAirport(savedTicket.getFlightId().getRouteId().getDepartureAirport().getName())
				.destinationAirport(savedTicket.getFlightId().getRouteId().getDestinationAirport().getName())
				.isDeleted(savedTicket.isDeleted()).build();

		return response;

	}

//	@Transactional
//	public void softDeleteTicketById(Long id) {
//
//		Ticket ticket = ticketRepository.findById(id)
//				.orElseThrow(() -> new TicketNotFountException("Ticket could not found!"));
//		if (ticket != null) {
//			ticket.setDeleted(true);
//			ticketRepository.save(ticket);
//		}
//	}
	
	

	
	public void softDeleteTicketByTicketNumber(String ticketNumber) {

		Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber)
				.orElseThrow(() -> new TicketNotFountException("Ticket could not found!"));
		if (ticket != null) {
			ticket.setDeleted(true);
			ticketRepository.save(ticket);
		}
	}
	
	
	public String maskCreditCard(String creditCardNumber) {

		String[] cardNumberArray = creditCardNumber.split("\\D+");
		// except last four digit masking

		StringBuilder maskedCardNumber = new StringBuilder();

		for (int i = 0; i < cardNumberArray.length - 1; i++) {
			String piece = cardNumberArray[i];
			if (!piece.isEmpty()) {
				for (int j = 0; j < piece.length(); j++) {
					maskedCardNumber.append('*');
				}
			}
		}

		StringBuilder appendMaskedCardNumber = maskedCardNumber
				.append(creditCardNumber.substring(creditCardNumber.length() - 4));
		return appendMaskedCardNumber.toString();

	}

	private String generateTicketNumber() {

		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		String firstFiveDigit = uuidAsString.substring(0, 5);
		return "TICKET-" + firstFiveDigit;

	}

	private int generateSeatNumber() {

		int a = DomainConstants.TOTAL_SEAT_NUMBER;

		int number = random.nextInt(5);

		int seatNumber = 1;

		if (number != ticketRepository.count()) {

			seatNumber = number;
		}
		return seatNumber;

	}

	public List<Ticket> getAllTicket() {
		return ticketRepository.findAll();
	}

	public Ticket findTicketById(Long id) {
		return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFountException("Ticket could not found!"));
	}

	public Ticket findTicketByTicketNumber(String ticketNumber) {
		return ticketRepository.findByTicketNumber(ticketNumber)
				.orElseThrow(() -> new TicketNotFountException("Ticket could not found!"));

	}

}
