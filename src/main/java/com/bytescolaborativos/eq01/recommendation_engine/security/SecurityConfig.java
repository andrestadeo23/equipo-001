package com.bytescolaborativos.eq01.recommendation_engine.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Para APIs normalmente deshabilitamos CSRF
                .csrf(AbstractHttpConfigurer::disable)

                // 🔓 Por ahora TODO permitido mientras probamos
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // Deshabilitamos por completo login y basic auth
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
