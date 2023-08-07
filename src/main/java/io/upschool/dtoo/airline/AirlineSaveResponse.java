package io.upschool.dtoo.airline;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineSaveResponse {

	private String airlineCode;

	private String airlineName;

	//private List<String> airplane;
	
	//private Long flightId;
}
