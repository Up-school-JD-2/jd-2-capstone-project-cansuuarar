package io.upschool.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE ticket SET is is_deleted = true WHERE id=?") // @SQLDelete annotation to override the delete command
@Where(clause = "is_deleted=false") // ticket data with the value deleted = true won't be included within the results
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ticket_no", unique = true)
	private String ticketNumber;

	@Column(name = "card_number", nullable = false, unique = true)
	private String cardNumber;

	@Column(name = "passenger_name", nullable = false)
	private String passengerName;

	@Column(name = "is_purchased", nullable = false)
	private boolean isPurchased;

	@Column(name = "ticket_price")
	private String ticket_price;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "flight_id", nullable = false)
	private Flight flightId;

	@Column(name = "is_deleted")
	private boolean isDeleted = Boolean.FALSE;

}
