package com.conecta.conectamemory.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAdmin(
        @NotNull
        @NotBlank
        String login,
        @NotBlank
        @NotNull
        String senha,
        @NotBlank
        @NotNull
        String nome
) {
}
