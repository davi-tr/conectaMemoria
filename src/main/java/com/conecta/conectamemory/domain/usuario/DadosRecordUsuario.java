package com.conecta.conectamemory.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
