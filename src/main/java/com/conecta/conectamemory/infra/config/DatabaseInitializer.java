package com.conecta.conectamemory.infra.config;

import com.conecta.conectamemory.domain.usuario.Administrador;
import com.conecta.conectamemory.repository.AdministradorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class DatabaseInitializer {
    private final AdministradorRepository administradorRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DatabaseInitializer(AdministradorRepository administradorRepository, BCryptPasswordEncoder passwordEncoder) {
        this.administradorRepository = administradorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            if (administradorRepository.findByLogin("Geral").isEmpty()) {
                Administrador admin = new Administrador();
                admin.setLogin("Geral");
                admin.setPassword(passwordEncoder.encode("admin123"));
                administradorRepository.save(admin);
                System.out.println("Admin user 'Geral' created");
            }
        };
    }
}
