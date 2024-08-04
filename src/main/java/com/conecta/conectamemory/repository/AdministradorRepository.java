package com.conecta.conectamemory.repository;

import com.conecta.conectamemory.domain.usuario.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByLogin(String login);
}
