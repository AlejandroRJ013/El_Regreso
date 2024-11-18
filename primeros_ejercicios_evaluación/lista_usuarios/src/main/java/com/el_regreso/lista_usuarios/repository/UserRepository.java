package com.el_regreso.lista_usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.el_regreso.lista_usuarios.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT AUTO_INCREMENT " +
            "FROM INFORMATION_SCHEMA.TABLES " +
            "WHERE TABLE_NAME = 'lista_usuarios'", nativeQuery = true)
    Integer findNextId();

}