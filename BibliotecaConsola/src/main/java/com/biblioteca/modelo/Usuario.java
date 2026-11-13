package com.biblioteca.modelo;

public class Usuario {
    private String id;
    private String nombre;
    private String direccion;
    private String telefono;
    private boolean activo;
    public Usuario(String id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.activo = true;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    @Override
    public String toString() {
        return id + " - " + nombre + " - " + direccion + " - " + telefono
                + " (" + (activo ? "Activo" : "Inactivo") + ")";
    }
}
