package com.conecta.conectamemory.controller;

import com.conecta.conectamemory.domain.usuario.Administrador;
import com.conecta.conectamemory.domain.usuario.dto.DadosCadastroAdmin;
import com.conecta.conectamemory.domain.usuario.dto.DadosLoginAdmin;
import com.conecta.conectamemory.domain.usuario.dto.DadosLoginRepostaAdministrador;
import com.conecta.conectamemory.domain.usuario.dto.DadosRecordUsuario;
import com.conecta.conectamemory.domain.usuario.Usuario;
import com.conecta.conectamemory.infra.security.TokenService;
import com.conecta.conectamemory.repository.AdministradorRepository;
import com.conecta.conectamemory.repository.UsuarioRepository;
import com.conecta.conectamemory.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scoreboard")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final AdministradorRepository administradorRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteScoreboard(@PathVariable Long id){
        var usuario = usuarioRepository.findById(id);
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAllScoreboard(@RequestBody Confirm confirm){
        if(confirm.confirmed){
            usuarioRepository.deleteAll();
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody DadosLoginAdmin dados) {
        Administrador administrador = this.administradorRepository.findByLogin(dados.login()).orElseThrow(()-> new RuntimeException("Usuario não encontrado"));
        if(passwordEncoder.matches(dados.password(), administrador.getPassword())){
            String token = this.tokenService.generateToken(administrador);
            return ResponseEntity.ok(new DadosLoginRepostaAdministrador(administrador.getId(),administrador.getLogin(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    public static class Confirm{
        private boolean confirmed;
        public Confirm() {}
        public Confirm(boolean confirmed) {
            this.confirmed = confirmed;
        }
        public boolean getConfirmed() {
            return confirmed;
        }
    }

}
