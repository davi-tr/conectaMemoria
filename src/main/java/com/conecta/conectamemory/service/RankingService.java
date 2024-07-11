package com.conecta.conectamemory.service;

import com.conecta.conectamemory.domain.usuario.Usuario;
import com.conecta.conectamemory.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RankingService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Object> findAllUsuariosByLowerTempoGastoRanked() {
        List<Usuario> usuarios = usuarioRepository.findAllByLowerTempoGasto();

        usuarios.sort(Comparator.comparingDouble(Usuario::getTempoGasto));

        List<Object> usuariosRanking = IntStream.range(0, usuarios.size())
                .mapToObj(i -> {
                    Usuario usuario = usuarios.get(i);
                    return new Object() {
                        public final Long id = usuario.getId();
                        public final int posicao = i + 1;
                        public final String nome = usuario.getNome();
                        public final String setor = usuario.getSetor();
                        public final double tempoGasto = usuario.getTempoGasto();
                    };
                })
                .collect(Collectors.toList());

        return usuariosRanking;
    }
}
