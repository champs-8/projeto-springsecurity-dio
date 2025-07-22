package com.champs.spring_security.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "tab_user")
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 20, nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER) // EAGER para carregar os roles junto com o usuário
    @CollectionTable(name = "tab_user_roles", joinColumns = @JoinColumn(name = "id_user"))
    // Define a tabela de junção para os papéis
    @Column(name = "role_id", nullable = false)
    private List<String> roles = new ArrayList<>();

    // Construtor
    public AppUser(String name){
        this.name = name;
    }
}
