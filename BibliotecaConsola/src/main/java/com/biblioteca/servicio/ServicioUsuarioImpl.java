package com.biblioteca.servicio;

import com.biblioteca.excepciones.UsuarioDuplicadoException;
import com.biblioteca.excepciones.UsuarioNoEncontradoException;
import com.biblioteca.modelo.Usuario;
import com.biblioteca.excepciones.UsuarioNoEncontradoException;
import com.biblioteca.excepciones.UsuarioDuplicadoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioUsuarioImpl implements ServicioUsuario{
    private Map<String, Usuario> usuarios;
    public ServicioUsuarioImpl() {
        this.usuarios = new HashMap<>();
    }
    @Override
    public void agregarUsuario(Usuario usuario) throws UsuarioDuplicadoException {
        if (usuarios.containsKey(usuario.getId())) {
            throw new UsuarioDuplicadoException("Ya existe un usuario con ese ID.");
        }
        usuarios.put(usuario.getId(), usuario);
    }
    @Override
    public void eliminarUsuario(String id) throws UsuarioNoEncontradoException {
        if (!usuarios.containsKey(id)) {
            throw new UsuarioNoEncontradoException("Usuario con ID " + id + " no encontrado.");
        }
        usuarios.remove(id);
    }
    @Override
    public Usuario buscarPorId(String id) throws UsuarioNoEncontradoException {
        if (!usuarios.containsKey(id)) {
            throw new UsuarioNoEncontradoException("Usuario con ID " + id + " no encontrado.");
        }
        return usuarios.get(id);
    }
    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        List<Usuario> resultado = new ArrayList<>();
        for (Usuario u : usuarios.values()) {
            if (u.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(u);
            }
        }
        return resultado;
    }
    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios.values());
    }
    @Override
    public void activarUsuario(String id) throws UsuarioNoEncontradoException {
        Usuario u = buscarPorId(id);
        u.setActivo(true);
    }
    @Override
    public void desactivarUsuario(String id) throws UsuarioNoEncontradoException {
        Usuario u = buscarPorId(id);
        u.setActivo(false);
    }
}
