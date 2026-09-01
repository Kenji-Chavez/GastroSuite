package com.proyecto.gastrosuite.controller;

import com.proyecto.gastrosuite.model.Restaurante;
import com.proyecto.gastrosuite.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    // 1. LEER (Obtener todos los restaurantes)
    @GetMapping
    public ResponseEntity<List<Restaurante>> obtenerTodos() {
        return ResponseEntity.ok(restauranteService.obtenerTodos());
    }

    // 2. LEER (Obtener un restaurante por ID)
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> obtenerPorId(@PathVariable Long id) {
        return restauranteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. CREAR (Guardar un nuevo restaurante)
    @PostMapping
    public ResponseEntity<Restaurante> crear(@RequestBody Restaurante restaurante) {
        Restaurante nuevoRestaurante = restauranteService.guardar(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRestaurante);
    }

    // 4. ACTUALIZAR (Modificar un restaurante existente)
    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> actualizar(@PathVariable Long id, @RequestBody Restaurante restauranteDetalles) {
        return restauranteService.actualizar(id, restauranteDetalles)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. ELIMINAR (Borrar un restaurante por ID)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (restauranteService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
