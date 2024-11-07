package com.el_regreso.lista_usuarios.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Usuario con el ID '" + id + "' no encontrado");
    }
}