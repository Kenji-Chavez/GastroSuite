package com.proyecto.gastrosuite.service;

import com.proyecto.gastrosuite.model.Restaurante;
import com.proyecto.gastrosuite.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public List<Restaurante> obtenerTodos() {
        return restauranteRepository.findAll();
    }

    public Optional<Restaurante> obtenerPorId(Long id) {
        return restauranteRepository.findById(id);
    }

    public Restaurante guardar(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public Optional<Restaurante> actualizar(Long id, Restaurante restauranteDetalles) {
        return restauranteRepository.findById(id).map(restaurante -> {
            restaurante.setNombre(restauranteDetalles.getNombre());
            restaurante.setDireccion(restauranteDetalles.getDireccion());
            restaurante.setTelefono(restauranteDetalles.getTelefono());
            restaurante.setEstado(restauranteDetalles.getEstado());
            return restauranteRepository.save(restaurante);
        });
    }

    public boolean eliminar(Long id) {
        if (restauranteRepository.existsById(id)) {
            restauranteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
