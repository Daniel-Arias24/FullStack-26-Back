package com.example.fullstack26back.service;

import com.example.fullstack26back.dto.LoginRequest;
import com.example.fullstack26back.dto.RegisterRequest;
import com.example.fullstack26back.dto.UserResponse;
import com.example.fullstack26back.model.User;
import com.example.fullstack26back.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ── Registro ──────────────────────────────────────────────
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ya existe una cuenta con ese email");
        }

        // NOTA: En producción usa BCryptPasswordEncoder para hashear el password.
        // Por ahora se guarda en texto plano para simplificar el desarrollo inicial.
        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword(),   // ← reemplazar por encoder.encode(request.getPassword())
                User.Role.USER
        );

        User saved = userRepository.save(user);
        return new UserResponse(saved);
    }

    // ── Login ─────────────────────────────────────────────────
    public UserResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email o contraseña incorrectos"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Email o contraseña incorrectos");
        }

        return new UserResponse(user);
    }

    // ── Obtener usuario por ID ────────────────────────────────
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        return new UserResponse(user);
    }

    // ── Listar todos (solo ADMIN) ─────────────────────────────
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    // ── Eliminar ──────────────────────────────────────────────
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        userRepository.deleteById(id);
    }
}