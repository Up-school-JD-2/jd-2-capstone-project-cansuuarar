package io.upschool.entity;

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
@Table(name = "routes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "departure_airport_id", nullable = false)
	private Airport departureAirport;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "destination_airport_id", nullable = false)
	private Airport destinationAirport;
	

}

