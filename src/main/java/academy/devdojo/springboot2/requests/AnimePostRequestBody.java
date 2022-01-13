package academy.devdojo.springboot2.requests;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimePostRequestBody {
	@NotEmpty(message = "the anime name cannot be empty")
	@Schema(description = "this is the Animes's name", example = "Tensei Shittara Slime Datta Ken", required = true)
	private String name;
}
