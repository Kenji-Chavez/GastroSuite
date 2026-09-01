package com.proyecto.gastrosuite.controller;

import com.proyecto.gastrosuite.model.Reserva;
import com.proyecto.gastrosuite.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    // 1. LEER (Obtener todas las reservas)
    @GetMapping
    public ResponseEntity<List<Reserva>> obtenerTodas() {
        return ResponseEntity.ok(reservaService.obtenerTodas());
    }

    // 2. LEER (Obtener reserva por ID)
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerPorId(@PathVariable Long id) {
        return reservaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. CREAR (Guardar una nueva reserva)
    @PostMapping
    public ResponseEntity<Reserva> crear(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.guardar(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
    }

    // 4. ACTUALIZAR (Modificar una reserva existente)
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> actualizar(@PathVariable Long id, @RequestBody Reserva reservaDetalles) {
        return reservaService.actualizar(id, reservaDetalles)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. ELIMINAR (Borrar una reserva por ID)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (reservaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
