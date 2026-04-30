package com.example.FullStack_26_Back.Servicios;

import com.example.FullStack_26_Back.DTO.ActualizarUsuario;
import com.example.FullStack_26_Back.DTO.Login;
import com.example.FullStack_26_Back.DTO.Registro;
import com.example.FullStack_26_Back.DTO.UserResponse;
import com.example.FullStack_26_Back.Modelo.User;
import com.example.FullStack_26_Back.Repositorio.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ── CREATE: Registro de comprador ─────────────────────────
    public UserResponse register(Registro request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ya existe una cuenta con ese email");
        }
        User user = new User(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                User.Role.USER
        );
        return new UserResponse(userRepository.save(user));
    }

    // ── LOGIN ─────────────────────────────────────────────────
    public UserResponse login(Login request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email o contraseña incorrectos"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Email o contraseña incorrectos");
        }
        return new UserResponse(user);
    }

    // ── READ: obtener todos ───────────────────────────────────
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    // ── READ: obtener por ID ──────────────────────────────────
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));
        return new UserResponse(user);
    }

    // ── UPDATE ────────────────────────────────────────────────
    public UserResponse update(Long id, ActualizarUsuario request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));

        // Solo actualiza los campos que lleguen con valor
        if (request.getName() != null && !request.getName().isBlank()) {
            user.setName(request.getName());
        }
        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            if (!request.getEmail().equals(user.getEmail())
                    && userRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Ese email ya está en uso");
            }
            user.setEmail(request.getEmail());
        }
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return new UserResponse(userRepository.save(user));
    }

    // ── DELETE ────────────────────────────────────────────────
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado con id: " + id);
        }
        userRepository.deleteById(id);
    }
}