package com.biblioteca.servicio;
import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.modelo.Usuario;
import com.biblioteca.excepciones.LibroNoEncontradoException;
import com.biblioteca.excepciones.LibroPrestadoException;
import com.biblioteca.excepciones.PrestamoNoEncontradoException;
import com.biblioteca.excepciones.UsuarioInactivoException;
import com.biblioteca.excepciones.UsuarioNoEncontradoException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicioPrestamoImpl implements ServicioPrestamo{
    private List<Prestamo> prestamos;
    private ServicioLibro servicioLibro;
    private ServicioUsuario servicioUsuario;


    public ServicioPrestamoImpl(ServicioLibro servicioLibro, ServicioUsuario servicioUsuario) {
        this.prestamos = new ArrayList<>();
        this.servicioLibro = servicioLibro;
        this.servicioUsuario = servicioUsuario;
    }


    @Override
    public void prestarLibro(String isbn, String idUsuario) throws LibroNoEncontradoException, UsuarioNoEncontradoException, LibroPrestadoException, UsuarioInactivoException {
        Libro libro = servicioLibro.buscarPorIsbn(isbn);
        if (libro.isPrestado()) {
            throw new LibroPrestadoException("El libro ya está prestado.");
        }
        Usuario usuario = servicioUsuario.buscarPorId(idUsuario);
        if (!usuario.isActivo()) {
            throw new UsuarioInactivoException("El usuario no está activo.");
        }
        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucionPrevista = fechaPrestamo.plusDays(15);
        Prestamo prestamo = new Prestamo(isbn, idUsuario, fechaPrestamo, fechaDevolucionPrevista);
        prestamos.add(prestamo);
        servicioLibro.marcarPrestado(isbn);
    }

    @Override
    public void devolverLibro(String isbn) throws LibroNoEncontradoException, PrestamoNoEncontradoException {
        Prestamo prestamoActivo = null;
        for (Prestamo p : prestamos) {
            if (p.getIsbnLibro().equals(isbn) && p.getFechaDevolucionReal() == null) {
                prestamoActivo = p;
                break;
            }
        }
        if (prestamoActivo == null) {
            throw new PrestamoNoEncontradoException("No hay un préstamo activo para ese libro.");
        }
        prestamoActivo.setFechaDevolucionReal(LocalDate.now());
        servicioLibro.marcarDevuelto(isbn);

    }

    @Override
    public List<Prestamo> listarPrestamosActivos() {
        //Seguridad. evitar inyección de código
        List<Prestamo> activos = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.getFechaDevolucionReal() == null) {
                activos.add(p);
            }
        }
        return activos;
    }
}
