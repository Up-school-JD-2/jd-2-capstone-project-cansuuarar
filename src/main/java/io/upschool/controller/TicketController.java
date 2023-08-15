package io.upschool.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.upschool.dtoo.BaseResponse;
import io.upschool.dtoo.ticket.TicketSaveRequest;
import io.upschool.dtoo.ticket.TicketSaveResponse;
import io.upschool.entity.Ticket;
import io.upschool.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

	private final TicketService ticketService;

	@GetMapping
	public ResponseEntity<BaseResponse<Ticket>> getAllTickets() {
		List<Ticket> allTicket = ticketService.getAllTicket();
		BaseResponse<Ticket> response = BaseResponse.<Ticket>builder().status(HttpStatus.FOUND.value()).isSuccess(true)
				.listData(allTicket).build();
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{ticketNumber}")
	public ResponseEntity<Object> findTicketByNumber(@PathVariable("ticketNumber") String ticketNumber) {
		Ticket ticket = ticketService.findTicketByTicketNumber(ticketNumber);
		BaseResponse<Ticket> response = BaseResponse.<Ticket>builder().status(HttpStatus.FOUND.value()).isSuccess(true)
				.data(ticket).build();
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Object> buyTicket(@Valid @RequestBody TicketSaveRequest request) {
		TicketSaveResponse saveResponse = ticketService.purchaseTicket(request);
		BaseResponse<TicketSaveResponse> response = BaseResponse.<TicketSaveResponse>builder()
				.status(HttpStatus.CREATED.value()).isSuccess(true).data(saveResponse).build();
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{ticketNumber}")
	public ResponseEntity<Object> softDeleteTicket(@PathVariable String ticketNumber) {
		Ticket ticket = ticketService.softDeleteTicketByTicketNumber(ticketNumber);
		BaseResponse<Ticket> response = BaseResponse.<Ticket>builder().status(HttpStatus.CREATED.value())
				.isSuccess(true).data(ticket).build();
		// return ResponseEntity.ok("ticket number " + ticketNumber + " has been
		// cancelled.");
		return ResponseEntity.ok(response);

	}

}
