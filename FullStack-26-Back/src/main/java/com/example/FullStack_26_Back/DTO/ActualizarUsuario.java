package com.example.FullStack_26_Back.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

/**
 * Datos opcionales para actualizar un usuario (PUT /api/users/{id}).
 * Solo se actualizan los campos que lleguen con valor.
 */
public class ActualizarUsuario {

    private String name;

    @Email(message = "Email inválido")
    private String email;

    @Size(min = 6, message = "La contraseña debe tener mínimo 6 caracteres")
    private String password;

    public String getName()                  { return name; }
    public void setName(String name)         { this.name = name; }

    public String getEmail()                 { return email; }
    public void setEmail(String email)       { this.email = email; }

    public String getPassword()              { return password; }
    public void setPassword(String password) { this.password = password; }
}
