package com.proyecto.gastrosuite.controller;

import com.proyecto.gastrosuite.model.Mesa;
import com.proyecto.gastrosuite.service.MesaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    // 1. LEER (Obtener todas las mesas)
    @GetMapping
    public ResponseEntity<List<Mesa>> obtenerTodas() {
        return ResponseEntity.ok(mesaService.obtenerTodas());
    }

    // 2. LEER (Obtener mesa por ID)
    @GetMapping("/{id}")
    public ResponseEntity<Mesa> obtenerPorId(@PathVariable Long id) {
        return mesaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. CREAR (Guardar una nueva mesa)
    @PostMapping
    public ResponseEntity<Mesa> crear(@RequestBody Mesa mesa) {
        Mesa nuevaMesa = mesaService.guardar(mesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMesa);
    }

    // 4. ACTUALIZAR (Modificar una mesa)
    @PutMapping("/{id}")
    public ResponseEntity<Mesa> actualizar(@PathVariable Long id, @RequestBody Mesa mesaDetalles) {
        return mesaService.actualizar(id, mesaDetalles)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. ELIMINAR (Borrar una mesa)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (mesaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
