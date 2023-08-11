package io.upschool.entity;

import io.upschool.constants.DomainConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ticket_no", unique = true)
	private String ticketNumber ; 

	@Column(name = "card_number", nullable = false, unique = true)
	private String cardNumber; 

	@Column(name="passenger_name")
	private String passengerName;
	
	@Column(name = "is_purchased", nullable = false)
	private boolean isPurchased;

	@Column(name = "price", nullable = true)
	private Double price ;
	
//	@Column(name="seat_number")
//	private int seatNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight_id", nullable = false)
	private Flight flightId;
	
//	@OneToOne(mappedBy = "ticket")
//	private Payment payment;

}



