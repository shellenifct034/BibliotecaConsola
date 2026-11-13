package com.biblioteca.principal;
import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.Usuario;
import com.biblioteca.modelo.Prestamo;
import java.time.LocalDate;

public class TestModelo {
    public static void main(String[] args) {
        Libro libro = new Libro("978-3-16-148410-0", "El Quijote", "Cervantes", 1605);
        Usuario usuario = new Usuario("U001", "Ana", "Calle Mayor 1", "666111222");
        LocalDate hoy = LocalDate.now();
        LocalDate devolucionPrevista = hoy.plusDays(15);
        Prestamo prestamo = new Prestamo(libro.getIsbn(), usuario.getId(), hoy, devolucionPrevista);
        System.out.println(libro);
        System.out.println(usuario);
        System.out.println(prestamo);
    }


}
