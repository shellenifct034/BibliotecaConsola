package com.biblioteca.servicio;
import com.biblioteca.modelo.Usuario;
import com.biblioteca.excepciones.UsuarioDuplicadoException;
import com.biblioteca.excepciones.UsuarioNoEncontradoException;
import java.util.List;

public interface ServicioUsuario{
    void agregarUsuario(Usuario usuario) throws UsuarioDuplicadoException;


    void eliminarUsuario(String id) throws UsuarioNoEncontradoException;


    Usuario buscarPorId(String id) throws UsuarioNoEncontradoException;


    List<Usuario> buscarPorNombre(String nombre);


    List<Usuario> listarTodos();


    void activarUsuario(String id) throws UsuarioNoEncontradoException;


    void desactivarUsuario(String id) throws UsuarioNoEncontradoException;

}
