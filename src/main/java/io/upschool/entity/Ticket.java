package io.upschool.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ticket_number", nullable = false, unique = true)
	private String ticketNumber; // Benzersiz bilet numarası

	@Column(name = "card_number", nullable = false, unique = true)
	private String cardNumber; // Benzersiz card numarası

	@Column(name = "is_purchased", nullable = false)
	private boolean isPurchased;

	@Column(name = "price", nullable = false)
	private Double price;

	@ManyToOne
	@JoinColumn(name = "flight_id", nullable = false)
	private Flight flight;

}



