package com.champs.spring_security.config;

import com.champs.spring_security.service.SecurityDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // ✅ ATIVA @PreAuthorize
public class WebSecurityConfig {

    @Autowired
    private SecurityDatabaseService securityService;

    @Autowired
    public void globalUserDetailsService(AuthenticationManagerBuilder auth) throws Exception {
        // Configura o UserDetailsService para usar o banco de dados
        auth.userDetailsService(securityService)
            .passwordEncoder(NoOpPasswordEncoder.getInstance());
        // Usando NoOpPasswordEncoder para simplificar, mas não recomendado em produção
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/login").permitAll() // Permite acesso ao login apenas com POST
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated() // Qualquer outra requisição deve ser autenticada
                ).formLogin(Customizer.withDefaults()) // Usando o login padrão do Spring Security
//                ).httpBasic(Customizer.withDefaults())
                .build();
    }

    //irá usar banco de dados
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//        var user1 = User.withUsername("admin")
//                .password(encoder.encode("admin123"))
//                .roles("ADMINS")
//                .build();
//
//        var user2 = User.withUsername("user")
//                .password(encoder.encode("user123"))
//                .roles("USERS")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
