package io.upschool.dtoo.airport;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	private String code;

	@NotBlank(message="Airport name cannot be blank!")
	@NotNull(message = "Airport name cannot be null!'")
	private String name;
	
	private String location;

}
