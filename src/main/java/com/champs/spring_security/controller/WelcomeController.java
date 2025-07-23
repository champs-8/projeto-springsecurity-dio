package com.champs.spring_security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping
    public String Welcome(){
        return "Welcome to my Springboot controller";
    }
    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String users(){
        return "Authorized user";
    }

    @GetMapping("/managers")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String managers(){
        return "Authorized manager";
    }
}
