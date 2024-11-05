package com.el_regreso.lista_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.el_regreso.lista_usuarios.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}