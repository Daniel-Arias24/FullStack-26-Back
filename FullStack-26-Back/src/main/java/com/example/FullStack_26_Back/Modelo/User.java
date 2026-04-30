package com.example.FullStack_26_Back.Modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String name;

    @Email(message = "Email inválido")
    @NotBlank(message = "El email es obligatorio")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(nullable = false)
    private String password; // almacenado como hash BCrypt

    /**
     * USER  → comprador (se registra desde el formulario de la tienda)
     * ADMIN → vendedor  (se registra desde el popup de admin)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public enum Role { USER, ADMIN }

    public User() {}

    public User(String name, String email, String password, Role role) {
        this.name     = name;
        this.email    = email;
        this.password = password;
        this.role     = role;
    }

    public Long          getId()                      { return id; }
    public void          setId(Long id)               { this.id = id; }

    public String        getName()                    { return name; }
    public void          setName(String name)         { this.name = name; }

    public String        getEmail()                   { return email; }
    public void          setEmail(String email)       { this.email = email; }

    public String        getPassword()                { return password; }
    public void          setPassword(String password) { this.password = password; }

    public Role          getRole()                    { return role; }
    public void          setRole(Role role)           { this.role = role; }

    public LocalDateTime getCreatedAt()               { return createdAt; }
}