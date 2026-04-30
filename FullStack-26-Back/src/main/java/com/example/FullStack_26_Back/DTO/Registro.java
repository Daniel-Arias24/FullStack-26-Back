package com.example.FullStack_26_Back.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Datos que llegan desde el formulario de registro del front.
 * Los usuarios siempre se crean con rol USER.
 * El admin existe desde el inicio y NO se registra por este endpoint.
 */
public class Registro {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Email(message = "Email inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener mínimo 6 caracteres")
    private String password;

    // Sin campo "role": los usuarios siempre serán USER
    public String getName()                  { return name; }
    public void setName(String name)         { this.name = name; }

    public String getEmail()                 { return email; }
    public void setEmail(String email)       { this.email = email; }

    public String getPassword()              { return password; }
    public void setPassword(String password) { this.password = password; }
}