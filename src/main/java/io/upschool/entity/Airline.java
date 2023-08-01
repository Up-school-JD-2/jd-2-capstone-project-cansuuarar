package io.upschool.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "airlines")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Airline {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "airline_code", nullable = false, length = 50)
	private String airlineCode;

	@Column(name = "airline_name", nullable = false, length = 250)
	private String airlineName;

	@Column(name = "airplane", nullable = false, length = 150)
	private List<String> airplane;

	@OneToMany(fetch = FetchType.LAZY) //unidirectional oneToMany
	@JoinColumn(name="flight_id")
	private List<Flight> flights;

}
