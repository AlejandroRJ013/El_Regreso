package com.el_regreso.lista_usuarios.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super("Valor inválido :  " + message);
    }
}
