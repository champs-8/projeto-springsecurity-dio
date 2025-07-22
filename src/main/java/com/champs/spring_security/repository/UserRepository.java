package com.champs.spring_security.repository;

import com.champs.spring_security.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    //Metodo para encontrar usuário por nome de usuário por meio de uma consulta JPQL
    @Query("SELECT u FROM AppUser u JOIN FETCH u.roles WHERE u.username = (:username)")
    public AppUser findByUsername(@Param("username") String username);
}
