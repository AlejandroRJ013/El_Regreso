package com.el_regreso.lista_usuarios.dto;

import jakarta.validation.constraints.*;

public class UserDTO {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    public UserDTO() {
        this.id = 0L;
        this.nombre = "";
        this.email = "";
    }

    public UserDTO(Long id, String nombre, String email) {
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
