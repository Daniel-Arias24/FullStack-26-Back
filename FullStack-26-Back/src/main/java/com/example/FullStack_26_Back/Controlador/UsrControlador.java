package com.example.FullStack_26_Back.Controlador;

import com.example.FullStack_26_Back.DTO.ActualizarUsuario;
import com.example.FullStack_26_Back.DTO.ApiResponse;
import com.example.FullStack_26_Back.DTO.UserResponse;
import com.example.FullStack_26_Back.Servicios.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API REST para el CRUD de usuarios.
 * Pensada para ser consumida desde el panel de administrador.
 * El CREATE se hace a través de /api/auth/register.
 */
@RestController
@RequestMapping("/api/users")
public class UsrControlador {

    private final UserService userService;

    public UsrControlador(UserService userService) {
        this.userService = userService;
    }

    // ── READ ALL ──────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAll() {
        return ResponseEntity.ok(
                ApiResponse.ok("Usuarios obtenidos", userService.getAll()));
    }

    // ── READ ONE ──────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.ok("Usuario encontrado", userService.getById(id)));
    }

    // ── UPDATE ────────────────────────────────────────────────
    /**
     * Solo se actualizan los campos que lleguen con valor.
     * Ejemplo: { "name": "Nuevo nombre" } → solo cambia el nombre.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarUsuario request) {
        return ResponseEntity.ok(
                ApiResponse.ok("Usuario actualizado", userService.update(id, request)));
    }

    // ── DELETE ────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Usuario eliminado", null));
    }
}