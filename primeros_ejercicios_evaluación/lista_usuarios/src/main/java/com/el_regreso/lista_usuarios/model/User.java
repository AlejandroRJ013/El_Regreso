package com.el_regreso.lista_usuarios.model;

public class User {
    private Long id;
    private String nombre;
    private String email;

    public User() {
        this.id = 0L;
        this.nombre = "";
        this.email = "";
    }

    public User(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
