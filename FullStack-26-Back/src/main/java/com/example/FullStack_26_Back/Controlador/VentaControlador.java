package com.example.FullStack_26_Back.Controlador;

import com.example.FullStack_26_Back.DTO.ApiResponse;
import com.example.FullStack_26_Back.Modelo.Venta;
import com.example.FullStack_26_Back.Servicios.VentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints del panel de administrador para registrar y consultar ventas.
 * Corresponde al formulario registroVenta.html del front.
 *
 * POST   /api/ventas          → Registra una venta nueva
 * GET    /api/ventas          → Lista todas las ventas
 * GET    /api/ventas/{id}     → Obtiene una venta por ID
 * GET    /api/ventas/vendedor/{nombre} → Filtra por vendedor
 * DELETE /api/ventas/{id}     → Elimina una venta
 */
@RestController
@RequestMapping("/api/ventas")
public class VentaControlador {

    private final VentaService ventaService;

    public VentaControlador(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Venta>> registrar(
            @Valid @RequestBody Venta venta) {

        Venta guardada = ventaService.registrar(venta);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Venta registrada exitosamente", guardada));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venta>>> listarTodas() {
        return ResponseEntity.ok(
                ApiResponse.ok("Ventas obtenidas", ventaService.listarTodas()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Venta>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.ok("Venta encontrada", ventaService.getById(id)));
    }

    @GetMapping("/vendedor/{nombre}")
    public ResponseEntity<ApiResponse<List<Venta>>> porVendedor(
            @PathVariable String nombre) {

        return ResponseEntity.ok(
                ApiResponse.ok("Ventas del vendedor", ventaService.porVendedor(nombre)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        ventaService.eliminar(id);
        return ResponseEntity.ok(ApiResponse.ok("Venta eliminada", null));
    }
}