package io.upschool.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	
	@Column(name = "destination_date", nullable= false)
	private Date destinationDate;
	
	@Column(name = "departure_date", nullable= false)
	private Date departureDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="route_id", nullable = false)
	private Route routeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="airline_id", nullable = false)
	private Airline airlineId;
	
	
	

}
