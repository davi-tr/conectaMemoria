package com.conecta.conectamemory.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosLoginAdmin(
        @NotNull
        @NotBlank
        String login,
        @NotBlank
        @NotNull
        String password
) {
}
