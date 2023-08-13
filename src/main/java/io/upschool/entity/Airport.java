package io.upschool.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "airports")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "airport_code", unique = true, nullable = false, length = 50)
	private String code;

	@Column(name = "airport_name", unique = true, nullable = false, length = 250)
	private String name;
	
	@Column(name = "airport_location", unique = true, nullable = false, length = 50)
	private String location;

}
