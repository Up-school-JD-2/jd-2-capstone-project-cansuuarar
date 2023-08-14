package io.upschool.dtoo.airline;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirlineSaveResponse {

	private Long airlineId;

	private String airlineCode;

	private String airlineName;

}
