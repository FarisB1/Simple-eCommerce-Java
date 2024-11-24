package com.ahmedfaris.demo.Config;

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
        String[] staticResources = {
                "/css/**",
                "/images/**",
                "/fonts/**",
                "/scripts/**"
        };

        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/register","/login","/logout","/about","/contact","/shop").permitAll()
                        .requestMatchers("/about", "/contact","/shop","/shop/**","/checkout", "/orders", "/products").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/products","/products/add", "/products/edit/**", "/products/delete/**", "/users", "/users/**").hasRole("ADMIN") // Check for ROLE_ADMIN
                        .requestMatchers(staticResources).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
