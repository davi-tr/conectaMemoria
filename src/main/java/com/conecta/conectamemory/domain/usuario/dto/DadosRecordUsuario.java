package com.conecta.conectamemory.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosRecordUsuario(
        @NotNull
        @NotBlank
        String nome,
        @NotBlank
        @NotNull
        String setor,
        @NotBlank
        @NotNull
        double tempoGasto
) {
}
