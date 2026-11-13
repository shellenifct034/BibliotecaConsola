package com.biblioteca.excepciones;

public class UsuarioInactivoException extends Exception {
    public UsuarioInactivoException(String message) {
        super(message);
    }
}
