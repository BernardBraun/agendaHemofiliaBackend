package br.com.github.apihemofilia.apihemofilia.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record AddressDto(@NotBlank String streetName, @NotBlank String district, @NotBlank String postalCode,
		@NotBlank Integer cityId) {

}
