package com.biblioteca.servicio;
import com.biblioteca.modelo.Libro;
import com.biblioteca.excepciones.LibroDuplicadoException;
import com.biblioteca.excepciones.LibroNoEncontradoException;
import com.biblioteca.excepciones.LibroPrestadoException;
import java.util.List;

public interface ServicioLibro {
    void agregarLibro(Libro libro) throws LibroDuplicadoException;


    void eliminarLibro(String isbn) throws LibroNoEncontradoException, LibroPrestadoException;


    Libro buscarPorIsbn(String isbn) throws LibroNoEncontradoException;


    List<Libro> buscarPorTitulo(String titulo);


    List<Libro> listarTodos();


    void marcarPrestado(String isbn) throws LibroNoEncontradoException, LibroPrestadoException;


    void marcarDevuelto(String isbn) throws LibroNoEncontradoException;

}
