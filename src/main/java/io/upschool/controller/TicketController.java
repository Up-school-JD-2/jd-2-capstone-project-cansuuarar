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
	public ResponseEntity<Object> getAllTickets() {
		List<TicketSaveResponse> allTickets = ticketService.getAllTicket();
		BaseResponse<TicketSaveResponse> response = BaseResponse.<TicketSaveResponse>builder()
				.status(HttpStatus.FOUND.value()).isSuccess(true).listData(allTickets).build();
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/{ticketNumber}")
	public ResponseEntity<Object> findTicketByNumber(@PathVariable("ticketNumber") String ticketNumber) {
		TicketSaveResponse saveResponse = ticketService.findTicketByTicketNumber(ticketNumber);
		BaseResponse<TicketSaveResponse> response = BaseResponse.<TicketSaveResponse>builder()
				.status(HttpStatus.FOUND.value()).isSuccess(true).data(saveResponse).build();
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
		TicketSaveResponse saveResponse = ticketService.softDeleteTicketByTicketNumber(ticketNumber);
		BaseResponse<TicketSaveResponse> response = BaseResponse.<TicketSaveResponse>builder().status(HttpStatus.CREATED.value())
				.isSuccess(true).data(saveResponse).build();
		return ResponseEntity.ok(response);

	}

}
