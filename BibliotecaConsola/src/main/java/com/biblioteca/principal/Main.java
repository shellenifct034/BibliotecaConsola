package com.biblioteca.principal;
import com.biblioteca.modelo.Libro;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.modelo.Usuario;


import com.biblioteca.servicio.ServicioLibro;
import com.biblioteca.servicio.ServicioLibroImpl;
import com.biblioteca.servicio.ServicioPrestamo;
import com.biblioteca.servicio.ServicioPrestamoImpl;
import com.biblioteca.servicio.ServicioUsuario;
import com.biblioteca.servicio.ServicioUsuarioImpl;


import com.biblioteca.excepciones.LibroNoEncontradoException;
import com.biblioteca.excepciones.LibroDuplicadoException;
import com.biblioteca.excepciones.LibroPrestadoException;
import com.biblioteca.excepciones.PrestamoNoEncontradoException;
import com.biblioteca.excepciones.UsuarioInactivoException;
import com.biblioteca.excepciones.UsuarioNoEncontradoException;
import com.biblioteca.excepciones.UsuarioDuplicadoException;


import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ServicioLibro servicioLibro;
    private static ServicioUsuario servicioUsuario;
    private static ServicioPrestamo servicioPrestamo;
    private static Scanner scanner;


    public static void main(String[] args) {
        // 1. Inicializar servicios
        servicioLibro = new ServicioLibroImpl();
        servicioUsuario = new ServicioUsuarioImpl();
        servicioPrestamo = new ServicioPrestamoImpl(servicioLibro, servicioUsuario);
        scanner = new Scanner(System.in);


        // 2. Cargar datos de ejemplo
        inicializarDatos();



        // 3. Bucle principal
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 0);
        System.out.println("¡Hasta luego!");
        scanner.close();
    }
    //Método datos de ejemplo. Con base de datos esto sobra
    private static void inicializarDatos() {
        try {
            servicioLibro.agregarLibro(new Libro("978-3-16-148410-0", "El Quijote", "Cervantes", 1605));
            servicioLibro.agregarLibro(new Libro("978-84-376-0494-7", "Cien años de soledad", "García Márquez", 1967));
            servicioLibro.agregarLibro(new Libro("978-84-204-0163-1", "La sombra del viento", "Carlos Ruiz Zafón", 2001));


            servicioUsuario.agregarUsuario(new Usuario("U001", "Ana", "Calle Mayor 1", "666111222"));
            servicioUsuario.agregarUsuario(new Usuario("U002", "Luis", "Avenida Central 5", "666333444"));
            servicioUsuario.agregarUsuario(new Usuario("U003", "Marta", "Plaza España 3", "666555666"));
        } catch (LibroDuplicadoException | UsuarioDuplicadoException e) {
            System.out.println("Error al cargar datos iniciales: " + e.getMessage());
        }
    }
    //Menú general
    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE BIBLIOTECA ===");
        System.out.println("1. Gestión de libros");
        System.out.println("2. Gestión de usuarios");
        System.out.println("3. Gestión de préstamos");
        System.out.println("4. Listar todos los libros");
        System.out.println("5. Listar todos los usuarios");
        System.out.println("6. Listar préstamos activos");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");

    }
    //opción
    private static int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar el buffer
            return opcion;
        } catch (InputMismatchException e) {
            System.out.println("Debes introducir un número.");
            scanner.nextLine(); // limpiar el buffer
            return -1;
        }
    }
    //método para leer enteros con el try catch
    private static int leerEnteros(){
        while (true){
            System.out.println("Introduce un número: ");
            try {
                int entero=scanner.nextInt();
                scanner.nextLine();
                return entero;
            }catch (InputMismatchException e){
                System.out.println("Debes introducir un número: ");
                scanner.nextLine();
            }
        }
    }
    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                menuLibros();
                break;
            case 2:
                menuUsuarios();
                break;
            case 3:
                menuPrestamos();
                break;
            case 4:
                listarLibros();
                break;
            case 5:
                listarUsuarios();
                break;
            case 6:
                listarPrestamosActivos();
                break;
            case 0:
                break;
            default:
                System.out.println("Opción no válida.");
        }

    }
    //Métodos listar
    private static void listarLibros() {
        List<Libro> libros = servicioLibro.listarTodos();
        if (libros.isEmpty()) {
            System.out.println("No hay libros en el catálogo.");
        } else {
            for (Libro l : libros) {
                System.out.println(l);
            }
        }
    }
    private static void listarUsuarios() {
        List<Usuario> usuarios = servicioUsuario.listarTodos();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario u : usuarios) {
                System.out.println(u);
            }
        }
    }
    private static void listarPrestamosActivos() {
        List<Prestamo> prestamos = servicioPrestamo.listarPrestamosActivos();
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos activos.");
        } else {
            for (Prestamo p : prestamos) {
                System.out.println(p);
            }
        }
    }

    //Métodos menus
    private static void menuLibros(){
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE LIBROS ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Eliminar libro");
            System.out.println("3. Buscar libro por ISBN");
            System.out.println("4. Buscar libro por título");
            System.out.println("0. Volver al menú principal");
            System.out.println("Elige una opción: ");
            opcion = leerOpcion();
            try {
                switch (opcion) {
                    case 1:
                        agregarLibro();
                        break;
                    case 2:
                        eliminarLibro();
                        break;
                    case 3:
                        buscarLibroPorIsbn();
                        break;
                    case 4:
                        buscarLibroPorTitulo();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (LibroNoEncontradoException | LibroPrestadoException | LibroDuplicadoException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
    //Métodos auxiliares menuLibro
    private static void agregarLibro() throws LibroDuplicadoException {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Año de publicación: ");
        int anio = leerEnteros();
        Libro libro = new Libro(isbn, titulo, autor, anio);
        servicioLibro.agregarLibro(libro);
        System.out.println("Libro agregado correctamente.");
    }
    private static void eliminarLibro() throws LibroPrestadoException, LibroNoEncontradoException{
        System.out.print("ISBN del libro a eliminar: ");
        String isbn = scanner.nextLine();
        servicioLibro.eliminarLibro(isbn);
        System.out.println("Libro eliminado correctamente.");
    }
    private static void buscarLibroPorIsbn() throws LibroNoEncontradoException {
        System.out.print("ISBN a buscar: ");
        String isbn = scanner.nextLine();
        Libro libro = servicioLibro.buscarPorIsbn(isbn);
        System.out.println(libro);
    }
    private static void buscarLibroPorTitulo() {
        System.out.print("Título a buscar: ");
        String titulo = scanner.nextLine();
        List<Libro> libros = servicioLibro.buscarPorTitulo(titulo);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            for (Libro l : libros) {
                System.out.println(l);
            }
        }
    }
    /*No hay try-catch aquí. La excepción se propaga al menuLibros, que es quien muestra el mensaje.
Es el mismo patrón que utilizamos en prestarLibro(), que tampoco va a capturar y delega en menuPrestamos.
El programa seguirá dando errores hasta que terminemos todas las funciones y captura de excepciones*/
    private static void menuUsuarios() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE USUARIOS ---");
            System.out.println("1. Agregar usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Buscar usuario por ID");
            System.out.println("4. Buscar usuario por nombre");
            System.out.println("5. Activar/Desactivar usuario");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");
            opcion = leerOpcion();
            try {
                switch (opcion) {
                    case 1: agregarUsuario(); break;
                    case 2: eliminarUsuario(); break;
                    case 3: buscarUsuarioPorId(); break;
                    case 4: buscarUsuarioPorNombre(); break;
                    case 5: cambiarEstadoUsuario(); break;
                    case 0: break;
                    default: System.out.println("Opción no válida.");
                }
            } catch (UsuarioNoEncontradoException | UsuarioDuplicadoException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
    //Métodos auxiliares menuUsuarios
    private static void agregarUsuario() throws UsuarioDuplicadoException {
        System.out.print("ID del usuario: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        Usuario usuario = new Usuario(id, nombre, direccion, telefono);
        servicioUsuario.agregarUsuario(usuario);
        System.out.println("Usuario agregado correctamente.");
    }
    private static void eliminarUsuario() throws UsuarioNoEncontradoException {
        System.out.print("ID del usuario a eliminar: ");
        String id = scanner.nextLine();
        servicioUsuario.eliminarUsuario(id);
        System.out.println("Usuario eliminado correctamente.");
    }
    private static void buscarUsuarioPorId() throws UsuarioNoEncontradoException {
        System.out.print("ID a buscar: ");
        String id = scanner.nextLine();
        Usuario u = servicioUsuario.buscarPorId(id);
        System.out.println(u);
    }
    private static void buscarUsuarioPorNombre() {
        System.out.print("Nombre a buscar: ");
        String nombre = scanner.nextLine();
        List<Usuario> usuarios = servicioUsuario.buscarPorNombre(nombre);
        if (usuarios.isEmpty()) {
            System.out.println("No se encontraron usuarios con ese nombre.");
        } else {
            for (Usuario u : usuarios) {
                System.out.println(u);
            }
        }
    }
    private static void cambiarEstadoUsuario() throws UsuarioNoEncontradoException {
        System.out.print("ID del usuario: ");
        String id = scanner.nextLine();
        Usuario u = servicioUsuario.buscarPorId(id);
        System.out.println("Estado actual de " + u.getNombre() + ": "
                + (u.isActivo() ? "Activo" : "Inactivo"));
        System.out.print("Cambiar estado: ");
        int opcion = leerOpcion();
        if (opcion == 1) {
            servicioUsuario.activarUsuario(id);
            System.out.println("Usuario activado.");
        } else if (opcion == 2) {
            servicioUsuario.desactivarUsuario(id);
            System.out.println("Usuario desactivado.");
        } else {
            System.out.println("Opción no válida. No se ha cambiado el estado.");
        }
    }
    private static void menuPrestamos() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PRÉSTAMOS ---");
            System.out.println("1. Prestar libro");
            System.out.println("2. Devolver libro");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");
            opcion = leerOpcion();
            try {
                switch (opcion) {
                    case 1: prestarLibro(); break;
                    case 2: devolverLibro(); break;
                    case 0: break;
                    default: System.out.println("Opción no válida.");
                }
            } catch (LibroNoEncontradoException | UsuarioNoEncontradoException | LibroPrestadoException | UsuarioInactivoException | PrestamoNoEncontradoException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    //Métodos auxiliares menuPrestamo
    private static void devolverLibro() throws LibroNoEncontradoException, PrestamoNoEncontradoException {
        System.out.print("ISBN del libro a devolver: ");
        String isbn = scanner.nextLine();
        servicioPrestamo.devolverLibro(isbn);
        System.out.println("Devolución registrada correctamente.");
    }
    private static void prestarLibro() throws LibroNoEncontradoException, UsuarioNoEncontradoException,
            LibroPrestadoException, UsuarioInactivoException {
        System.out.print("ISBN del libro: ");
        String isbn = scanner.nextLine();
        System.out.print("ID del usuario: ");
        String idUsuario = scanner.nextLine();
        servicioPrestamo.prestarLibro(isbn, idUsuario);
        System.out.println("Préstamo registrado correctamente.");
    }
}
