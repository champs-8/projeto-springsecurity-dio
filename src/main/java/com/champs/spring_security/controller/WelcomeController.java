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
    @PreAuthorize("hasAnyRole('ADMINS', 'USERS')")
    public String users(){
        return "Authorized user";
    }

    @GetMapping("/managers")
    @PreAuthorize("hasAnyRole('ADMINS')")
    public String managers(){
        return "Authorized manager";
    }
}
