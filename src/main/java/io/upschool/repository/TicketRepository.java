package io.upschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.upschool.entity.Ticket;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	
	public Ticket findByTicketNumber(String ticketNumber);
}
