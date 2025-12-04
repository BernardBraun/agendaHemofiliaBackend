package br.com.github.apihemofilia.apihemofilia.domain.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(@NotBlank String login, @NotBlank String password) {

}
