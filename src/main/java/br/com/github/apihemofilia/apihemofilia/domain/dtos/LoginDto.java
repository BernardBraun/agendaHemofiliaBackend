package br.com.github.apihemofilia.apihemofilia.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(@NotBlank String login, @NotBlank String password) {

}
