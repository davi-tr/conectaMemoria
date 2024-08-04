package com.conecta.conectamemory.infra.security;

import com.conecta.conectamemory.domain.usuario.Administrador;
import com.conecta.conectamemory.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private AdministradorRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = this.repository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
        return new org.springframework.security.core.userdetails.User(usuario.getLogin(), usuario.getPassword(), new ArrayList<>());
    }

}
