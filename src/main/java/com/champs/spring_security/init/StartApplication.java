package com.champs.spring_security.init;

import com.champs.spring_security.model.AppUser;
import com.champs.spring_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class StartApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        System.out.println("==> Inicializando usuários...");

        AppUser user = userRepository.findByUsername("admin");
        if(user == null) {
            user = new AppUser();
            user.setName("ADMIN");
            user.setUsername("admin");
            user.setPassword("{noop}admin123"); // {noop} indica que a senha não está criptografada
            user.getRoles().add("ADMIN");
            userRepository.save(user);
        }

        user = userRepository.findByUsername("user");
        if(user == null) {
            user = new AppUser();
            user.setName("USER");
            user.setUsername("user");
            user.setPassword("{noop}user123"); // {noop} indica que a senha não está criptografada
            user.getRoles().add("USER");
            userRepository.save(user);
        }
    }
}
