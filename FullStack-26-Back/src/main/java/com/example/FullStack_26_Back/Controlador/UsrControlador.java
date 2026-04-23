package com.example.FullStack_26_Back.Controlador;

import com.example.FullStack_26_Back.DTO.ApiResponse;
import com.example.FullStack_26_Back.DTO.UserResponse;
import com.example.FullStack_26_Back.Servicios.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Operaciones sobre usuarios (pensadas para uso del ADMIN).
 *
 * GET    /api/users       → Lista todos los usuarios
 * GET    /api/users/{id}  → Obtiene un usuario por ID
 * DELETE /api/users/{id}  → Elimina un usuario
 */
@RestController
@RequestMapping("/api/users")
public class UsrControlador {

    private final UserService userService;

    public UsrControlador(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok("Usuarios obtenidos", userService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok("Usuario encontrado", userService.getById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok(ApiResponse.ok("Usuario eliminado", null));
    }
}