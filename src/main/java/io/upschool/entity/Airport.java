package io.upschool.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	@Column(name = "airport_code", nullable = false, length = 50)
	private String code;

	@Column(name = "airport_name", nullable = false, length = 250)
	private String name;

	@OneToMany(mappedBy = "airports")
	private List<Route> route;

}
