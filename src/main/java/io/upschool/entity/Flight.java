package io.upschool.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "flights")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonDeserialize(as = LocalDate.class)
	@Column(name = "departure_date", nullable = false)
	private LocalDateTime departureDate;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonDeserialize(as = LocalDate.class)
	@Column(name = "arrival_date", nullable = false)
	private LocalDateTime arrivalDate;
	
	@Column(name="total_seat", nullable = false)
	private int totalSeat;

	// manytomany, manyToOne
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "route_id", nullable = false)
	private Route routeId;
	
	//airline
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "airline_id", nullable = false)
	private Airline airlineId;
	
	@OneToMany(mappedBy = "flight")
	private List<Ticket> tickets;
	
	
	

}
