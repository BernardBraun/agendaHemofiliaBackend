package br.com.github.apihemofilia.apihemofilia.domain.dtos;

import java.time.LocalDateTime;

import br.com.github.apihemofilia.apihemofilia.enums.BleedTypeLocal;
import br.com.github.apihemofilia.apihemofilia.enums.Reason;
import jakarta.validation.constraints.NotBlank;

public record DiaryDto(@NotBlank LocalDateTime infusionDate, @NotBlank Reason reason,
		@NotBlank BleedTypeLocal bleedTypeLocal, @NotBlank Integer treatment_id, @NotBlank String observation,
		@NotBlank Long personId, @NotBlank Integer hemocenterId) {

}
