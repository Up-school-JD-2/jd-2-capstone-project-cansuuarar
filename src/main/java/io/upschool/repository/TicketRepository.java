package io.upschool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.upschool.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	public Optional<Ticket> findByTicketNumber(String ticketNumber);
	
	Ticket findTicketByPassengerName(@Param("passengerName" )String passengerName);

}
