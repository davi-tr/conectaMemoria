package com.conecta.conectamemory.domain.usuario;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String setor;
    private double tempoGasto;

    public Usuario(DadosRecordUsuario dados){
        this.nome = dados.nome();
        this.setor = dados.setor();
        this.tempoGasto = dados.tempoGasto();
    }
}
