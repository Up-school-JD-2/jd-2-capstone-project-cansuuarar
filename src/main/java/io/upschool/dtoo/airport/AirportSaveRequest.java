package io.upschool.dtoo.airport;



import io.upschool.constants.DomainConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportSaveRequest {

	@NotBlank(message="Airport code cannot be blank!")
	@NotNull(message = "Airport code cannot be null!'")
	@Size(min=DomainConstants.AIRPORT_IATA_CODE, max=DomainConstants.AIRPORT_IATA_CODE, message="Airline code size must be 3.")
	private String code;

	@NotBlank(message="Airport name cannot be blank!")
	@NotNull(message = "Airport name cannot be null!'")
	private String name;
	
	private String location;

}
