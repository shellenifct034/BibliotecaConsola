package com.biblioteca.servicio;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.excepciones.LibroNoEncontradoException;
import com.biblioteca.excepciones.UsuarioNoEncontradoException;
import com.biblioteca.excepciones.LibroPrestadoException;
import com.biblioteca.excepciones.UsuarioInactivoException;
import com.biblioteca.excepciones.PrestamoNoEncontradoException;
import java.util.List;

public interface ServicioPrestamo {
    void prestarLibro(String isbn, String idUsuario)
            throws LibroNoEncontradoException,
            UsuarioNoEncontradoException,
            LibroPrestadoException,
            UsuarioInactivoException;


    void devolverLibro(String isbn)
            throws LibroNoEncontradoException,
            PrestamoNoEncontradoException;


    List<Prestamo> listarPrestamosActivos();

}
