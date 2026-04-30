package com.example.FullStack_26_Back.Controlador;

import com.example.FullStack_26_Back.DTO.ApiResponse;
import com.example.FullStack_26_Back.DTO.Login;
import com.example.FullStack_26_Back.DTO.Registro;
import com.example.FullStack_26_Back.DTO.UserResponse;
import com.example.FullStack_26_Back.Servicios.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints públicos de autenticación.
 *
 * POST /api/auth/register  → Registra un nuevo comprador (rol USER)
 * POST /api/auth/login     → Login para compradores y admin
 *                            La respuesta incluye "role": "USER" o "ADMIN"
 *                            para que el front sepa a dónde redirigir.
 */
@RestController
@RequestMapping("/api/auth")
public class AutenticacionController {

    private final UserService userService;

    public AutenticacionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(
            @Valid @RequestBody Registro request) {
        UserResponse user = userService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Usuario registrado exitosamente", user));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(
            @Valid @RequestBody Login request) {
        UserResponse user = userService.login(request);
        return ResponseEntity.ok(ApiResponse.ok("Login exitoso", user));
    }
}