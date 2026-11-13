package com.biblioteca.servicio;
import com.biblioteca.modelo.Libro;
import com.biblioteca.excepciones.LibroNoEncontradoException;
import com.biblioteca.excepciones.LibroPrestadoException;
import com.biblioteca.excepciones.LibroDuplicadoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioLibroImpl implements ServicioLibro{
    private Map<String, Libro> libros;
    public ServicioLibroImpl() {
        this.libros = new HashMap<>();
    }

    @Override
    public void agregarLibro(Libro libro) throws LibroDuplicadoException {
        if (libros.containsKey(libro.getIsbn())) {
            throw new LibroDuplicadoException("Ya existe un libro con ese ISBN.");
        }
        libros.put(libro.getIsbn(), libro);
    }

    @Override
    public void eliminarLibro(String isbn) throws LibroNoEncontradoException, LibroPrestadoException {
        if (!libros.containsKey(isbn)) {
            throw new LibroNoEncontradoException("Libro con ISBN " + isbn + " no encontrado.");
        }
        if (libros.get(isbn).isPrestado()) {
            throw new LibroPrestadoException("No se puede eliminar un libro prestado.");
        }
        libros.remove(isbn);
    }

    @Override
    public Libro buscarPorIsbn(String isbn) throws LibroNoEncontradoException {
        if (!libros.containsKey(isbn)) {
            throw new LibroNoEncontradoException("Libro con ISBN " + isbn + " no encontrado.");
        }
        return libros.get(isbn);
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> resultado = new ArrayList<>();
        for (Libro l : libros.values()) {
            if (l.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    @Override
    public List<Libro> listarTodos() {
        return new ArrayList<>(libros.values());
    }

    @Override
    public void marcarPrestado(String isbn) throws LibroNoEncontradoException, LibroPrestadoException {
        Libro libro = buscarPorIsbn(isbn);
        if (libro.isPrestado()) {
            throw new LibroPrestadoException("El libro ya está prestado.");
        }
        libro.setPrestado(true);
    }

    @Override
    public void marcarDevuelto(String isbn) throws LibroNoEncontradoException {
        Libro libro = buscarPorIsbn(isbn);
        libro.setPrestado(false);
    }
}
