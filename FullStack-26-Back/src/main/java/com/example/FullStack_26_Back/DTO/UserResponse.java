package com.example.FullStack_26_Back.DTO;

import com.example.FullStack_26_Back.Modelo.User;
import java.time.LocalDateTime;

/**
 * Lo que el back devuelve al front tras login o registro.
 * Nunca expone la contraseña.
 * El campo "role" es clave: el front lo usa para decidir
 * si mostrar el panel de admin (ADMIN) o el de comprador (USER).
 */
public class UserResponse {

    private Long          id;
    private String        name;
    private String        email;
    private String        role;       // "USER" o "ADMIN"
    private LocalDateTime createdAt;

    public UserResponse(User user) {
        this.id        = user.getId();
        this.name      = user.getName();
        this.email     = user.getEmail();
        this.role      = user.getRole().name();
        this.createdAt = user.getCreatedAt();
    }

    public Long          getId()        { return id; }
    public String        getName()      { return name; }
    public String        getEmail()     { return email; }
    public String        getRole()      { return role; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}