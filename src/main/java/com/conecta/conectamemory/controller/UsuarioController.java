package com.conecta.conectamemory.controller;

import com.conecta.conectamemory.domain.usuario.DadosRecordUsuario;
import com.conecta.conectamemory.domain.usuario.Usuario;
import com.conecta.conectamemory.repository.UsuarioRepository;
import com.conecta.conectamemory.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scoreboard")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    @Autowired
    private RankingService rankingService;

    @PostMapping()
    public ResponseEntity createScoreboard(@RequestBody DadosRecordUsuario dados) {
        Usuario usuario = new Usuario(dados);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity getScoreboard() {
        List<Object> usuarios = rankingService.findAllUsuariosByLowerTempoGastoRanked();
        return ResponseEntity.ok().body(usuarios);
    }
}
