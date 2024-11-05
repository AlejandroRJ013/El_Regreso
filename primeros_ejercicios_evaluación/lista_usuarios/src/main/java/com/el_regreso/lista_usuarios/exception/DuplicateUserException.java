package com.el_regreso.lista_usuarios.exception;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message) {
        super("Usuario duplicado :  " + message);
    }
}
