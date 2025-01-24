package com.sali.EmployeeManagement.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**").permitAll() // Allow access to home, login, and static resources
                        .requestMatchers("/admin/**").hasAuthority("ADMIN") // Only ADMIN can access /admin/**
                        .requestMatchers("/user/**").hasAuthority("USER") // Only USER can access /user/**
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .permitAll()
                        .defaultSuccessUrl("/", true) // Redirect to home page after successful login
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Logout endpoint
                        .logoutSuccessUrl("/login?logout") // Redirect to login page after logout
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}