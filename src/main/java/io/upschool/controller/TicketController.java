package io.upschool.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

	private final TicketService ticketService;

	@GetMapping
	public ResponseEntity<List<Ticket>> getAllTickets() {
		var allTickets = ticketService.getAllTicket();
		return ResponseEntity.ok(allTickets);
	}
	
	
	@GetMapping(path = "/{ticketId}")
	public ResponseEntity<Ticket> findTicket(@PathVariable("ticketId") Long ticketId) {
		return ResponseEntity.ok(ticketService.findTicketById(ticketId));
	}
	
	@PostMapping
	public ResponseEntity<Object> buyTicket(@RequestBody TicketSaveRequest request){
		
		 var ticketSaveResponse = ticketService.purchaseTicket(request);
		 
		 var response = BaseResponse.<TicketSaveResponse>builder()
		 .status(HttpStatus.CREATED.value())
		 .isSuccess(true)
		 .data(ticketSaveResponse)
		 .build();
		
		return ResponseEntity.ok(response);
		
	}
	
	

}
