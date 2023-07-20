package br.com.github.apihemofilia.apihemofilia.domain.dtos;

import java.time.LocalDate;

import br.com.github.apihemofilia.apihemofilia.enums.HemophiliaType;
import jakarta.validation.constraints.NotBlank;

public record PersonDto(@NotBlank String completeName, @NotBlank LocalDate birthDate, @NotBlank Float height,
						@NotBlank Float weight, @NotBlank HemophiliaType hemophiliaType, @NotBlank Integer infusionDays,
						@NotBlank String cellPhone, @NotBlank String email, @NotBlank String fatherName, @NotBlank String motherName,
						@NotBlank boolean inhibitor, @NotBlank Integer hemocenterId, @NotBlank AddressDto address, @NotBlank LoginDto login) {

}
