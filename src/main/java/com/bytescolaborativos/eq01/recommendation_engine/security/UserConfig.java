package com.bytescolaborativos.eq01.recommendation_engine.security;

import com.bytescolaborativos.eq01.recommendation_engine.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfig {

    @Bean
    public UserDetailsService userDetailService(UserRepository userRepository) {
        return username -> userRepository.findByUsername(username)
                .map(user ->
                        org.springframework.security.core.userdetails.User.builder()
                                .username(user.getUsername())
                                .password(user.getPassword())
                                .roles(
                                        user.getRoles().stream()
                                                .map(Enum::name)       // ADMIN, PLAYER
                                                .toArray(String[]::new)
                                )
                                .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
