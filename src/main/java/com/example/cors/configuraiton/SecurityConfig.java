package com.example.cors.configuraiton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable and configure CORS
                .csrf(AbstractHttpConfigurer::disable)  // Correct way to disable CSRF in 6.1+
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/hello/**").permitAll()   // Allow public access to /hello
                        .anyRequest().authenticated()            // Secure all other endpoints
                )
                .formLogin(AbstractHttpConfigurer::disable); // Disable default login form
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Allow specific domains only (e.g., frontend and admin app domains)
        configuration.setAllowedOrigins(List.of("https://localhost:8444"));

        // Define allowed HTTP methods
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        // Preflight cache duration (1 hour)
        configuration.setMaxAge(3600L);

        // Register the configuration for all routes ("/**")
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
