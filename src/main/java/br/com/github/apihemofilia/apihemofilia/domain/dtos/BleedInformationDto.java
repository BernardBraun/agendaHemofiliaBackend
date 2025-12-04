package br.com.github.apihemofilia.apihemofilia.domain.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record BleedInformationDto(@NotNull LocalDate bleedDate, @NotNull String bleedLocal,
        @NotNull String bleedTreatment,
        String observation, @NotNull Long personId) {

}
