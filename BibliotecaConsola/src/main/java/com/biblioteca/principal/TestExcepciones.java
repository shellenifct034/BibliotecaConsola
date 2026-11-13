package com.biblioteca.principal;
import com.biblioteca.excepciones.LibroNoEncontradoException;

public class TestExcepciones {
    public static void main(String[] args) {
        try {
            buscarLibro("978-3-16-148410-0");
        } catch (LibroNoEncontradoException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
    }
    private static void buscarLibro(String isbn) throws LibroNoEncontradoException {
        // Simulamos que el libro no existe
        throw new LibroNoEncontradoException("Libro con ISBN " + isbn + " no encontrado.");
    }
}
