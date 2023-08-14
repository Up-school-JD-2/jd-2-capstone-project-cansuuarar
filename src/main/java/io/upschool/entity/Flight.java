package io.upschool.entity;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
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
@Table(name = "flights")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "flight_number", nullable=false)
	private String flightNumber;
	
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape=JsonFormat.Shape.STRING)
	@Column(name = "departure_date", nullable = false)
	private LocalDateTime departureDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape=JsonFormat.Shape.STRING)
	@Column(name = "arrival_date", nullable = false)
	private LocalDateTime arrivalDate;
	
	@Column(name="total_seat")
	private int totalSeat;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "route_id", nullable = false)
	private Route routeId;
	
	@ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
	@JoinColumn(name = "airline_id", nullable = false)
	private Airline airlineId;
	
}
