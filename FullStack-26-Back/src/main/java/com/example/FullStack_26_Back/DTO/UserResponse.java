package com.example.FullStack_26_Back.DTO;

import com.example.FullStack_26_Back.Modelo.User;
import java.time.LocalDateTime;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String role;
    private LocalDateTime createdAt;

    public UserResponse(User user) {
        this.id        = user.getId();
        this.name      = user.getName();
        this.email     = user.getEmail();
        this.role      = user.getRole().name();
        this.createdAt = user.getCreatedAt();
    }

    // Getters
    public Long getId()                 { return id; }
    public String getName()             { return name; }
    public String getEmail()            { return email; }
    public String getRole()             { return role; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}