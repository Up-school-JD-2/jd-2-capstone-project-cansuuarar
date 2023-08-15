package io.upschool.service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.upschool.dtoo.ticket.TicketSaveRequest;
import io.upschool.dtoo.ticket.TicketSaveResponse;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
import io.upschool.exception.route.RouteAlreadySavedException;
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

		checkTicketIsAlreadyPurchased(request);

		Flight flightReferenceById = flightService.getReferenceById(request.getFlightId());

		// Checks if flight_id in request is exist in database. If does not exist throws
		// a not found exception.
		flightService.findFlightById(request.getFlightId());

		Ticket ticket = Ticket.builder().passengerName(request.getPassengerName())
				.cardNumber(maskCreditCard(request.getCardNumber())).flightId(flightReferenceById)
				.ticketNumber(generateTicketNumber()).ticket_price(request.getTicketPrice() + " TL").isPurchased(true)
				.build();

		Ticket savedTicket = ticketRepository.save(ticket);

		TicketSaveResponse response = TicketSaveResponse.builder().ticketId(savedTicket.getId())
				.passengerName(savedTicket.getPassengerName()).cardNumber(savedTicket.getCardNumber()).isPurchased(true)
				.ticketNumber(savedTicket.getTicketNumber()).ticketPrice(savedTicket.getTicket_price())
				.flightId(savedTicket.getFlightId().getId()).flightNumber(savedTicket.getFlightId().getFlightNumber())
				.departureAirport(savedTicket.getFlightId().getRouteId().getDepartureAirport().getName())
				.destinationAirport(savedTicket.getFlightId().getRouteId().getDestinationAirport().getName())
				.isDeleted(savedTicket.isDeleted()).build();

		return response;

	}

	private void checkTicketIsAlreadyPurchased(TicketSaveRequest request) {
		Ticket ticket = ticketRepository.findTicketByPassengerName(request.getPassengerName());
		System.out.println(ticket);
		if (ticket != null) {
			System.out.println(ticket);
			throw new RouteAlreadySavedException("This ticket has been already purchased by same person!");
		}
	}

	public TicketSaveResponse softDeleteTicketByTicketNumber(String ticketNumber) {

		Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber)
				.orElseThrow(() -> new TicketNotFountException("Ticket could not found!"));
		if (ticket != null) {
			ticket.setDeleted(true);
			ticketRepository.save(ticket);
		}
		return TicketSaveResponse.builder().ticketId(ticket.getId())
				.passengerName(ticket.getPassengerName()).cardNumber(ticket.getCardNumber()).isPurchased(true)
				.ticketNumber(ticket.getTicketNumber()).ticketPrice(ticket.getTicket_price())
				.flightId(ticket.getFlightId().getId()).flightNumber(ticket.getFlightId().getFlightNumber())
				.departureAirport(ticket.getFlightId().getRouteId().getDepartureAirport().getName())
				.destinationAirport(ticket.getFlightId().getRouteId().getDestinationAirport().getName())
				.isDeleted(ticket.isDeleted()).build();
	}

	public String maskCreditCard(String creditCardNumber) {

		String[] cardNumberArray = creditCardNumber.split("\\D+");
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

	public List<TicketSaveResponse> getAllTicket() {
		List<Ticket> tickets = ticketRepository.findAll();
		return tickets.stream()
				.map(ticket -> new TicketSaveResponse(ticket.getId(), ticket.getPassengerName(), ticket.getCardNumber(),
						ticket.isPurchased(), ticket.getTicketNumber(), ticket.getTicket_price(),
						ticket.getFlightId().getId(), ticket.getFlightId().getFlightNumber(),
						ticket.getFlightId().getRouteId().getDepartureAirport().getName(),
						ticket.getFlightId().getRouteId().getDestinationAirport().getName(), ticket.isDeleted()))
				.collect(Collectors.toList());
	}

	public Ticket findTicketById(Long id) {
		return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFountException("Ticket could not found!"));
	}

	public TicketSaveResponse findTicketByTicketNumber(String ticketNumber) {
		Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber)
				.orElseThrow(() -> new TicketNotFountException("Ticket could not found!"));
		return TicketSaveResponse.builder().ticketId(ticket.getId()).passengerName(ticket.getPassengerName())
				.cardNumber(ticket.getCardNumber()).isPurchased(true).ticketNumber(ticket.getTicketNumber())
				.ticketPrice(ticket.getTicket_price()).flightId(ticket.getFlightId().getId())
				.flightNumber(ticket.getFlightId().getFlightNumber())
				.departureAirport(ticket.getFlightId().getRouteId().getDepartureAirport().getName())
				.destinationAirport(ticket.getFlightId().getRouteId().getDestinationAirport().getName())
				.isDeleted(ticket.isDeleted()).build();
	}

}
