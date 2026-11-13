package com.biblioteca.modelo;
import java.time.LocalDate;

public class Prestamo {
    private String isbnLibro;
    private String idUsuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private LocalDate fechaDevolucionReal;
    public Prestamo(String isbnLibro, String idUsuario,
                    LocalDate fechaPrestamo, LocalDate fechaDevolucionPrevista) {
        this.isbnLibro = isbnLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
        this.fechaDevolucionReal = null;
    }
    public String getIsbnLibro() { return isbnLibro; }
    public String getIdUsuario() { return idUsuario; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public LocalDate getFechaDevolucionPrevista() { return fechaDevolucionPrevista; }
    public LocalDate getFechaDevolucionReal() { return fechaDevolucionReal; }
    public void setFechaDevolucionReal(LocalDate fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }
    @Override
    public String toString() {
        String estado = (fechaDevolucionReal == null)
                ? "Activo, devolución prevista: " + fechaDevolucionPrevista
                : "Devuelto el " + fechaDevolucionReal;
        return "Libro " + isbnLibro + " - Usuario " + idUsuario + " - " + estado;
    }

}
