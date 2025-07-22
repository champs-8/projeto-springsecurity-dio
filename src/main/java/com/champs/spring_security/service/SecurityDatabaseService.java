package com.champs.spring_security.service;

import com.champs.spring_security.model.AppUser;
import com.champs.spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SecurityDatabaseService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aqui você deve implementar a lógica para buscar o usuário no banco de dados
        // Por exemplo, usando um repositório JPA ou outro mecanismo de persistência.
        // Se o usuário não for encontrado, lance uma UsernameNotFoundException.
        AppUser userEntity = userRepository.findByUsername(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        // Converter o usuário encontrado em UserDetails
        Set<GrantedAuthority> authorities = userEntity.getRoles().stream()
//                .map(role -> (GrantedAuthority) () -> "ROLE_" + role)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());


        return new User(userEntity.getUsername(),
                userEntity.getPassword(), authorities);
    }
}
