package com.proyecto.gastrosuite.service;

import com.proyecto.gastrosuite.model.Mesa;
import com.proyecto.gastrosuite.model.Restaurante;
import com.proyecto.gastrosuite.repository.MesaRepository;
import com.proyecto.gastrosuite.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;
    private final RestauranteRepository restauranteRepository;

    public MesaService(MesaRepository mesaRepository, RestauranteRepository restauranteRepository) {
        this.mesaRepository = mesaRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public List<Mesa> obtenerTodas() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> obtenerPorId(Long id) {
        return mesaRepository.findById(id);
    }

    public Mesa guardar(Mesa mesa) {
        if (mesa.getRestaurante() != null && mesa.getRestaurante().getIdRestaurante() != null) {
            Restaurante r = restauranteRepository.findById(mesa.getRestaurante().getIdRestaurante()).orElse(mesa.getRestaurante());
            mesa.setRestaurante(r);
        }
        return mesaRepository.save(mesa);
    }

    public Optional<Mesa> actualizar(Long id, Mesa mesaDetalles) {
        return mesaRepository.findById(id).map(mesa -> {
            mesa.setNumero(mesaDetalles.getNumero());
            mesa.setCapacidad(mesaDetalles.getCapacidad());
            mesa.setEstado(mesaDetalles.getEstado());
            if (mesaDetalles.getRestaurante() != null) {
                mesa.setRestaurante(mesaDetalles.getRestaurante());
            }
            return mesaRepository.save(mesa);
        });
    }

    public boolean eliminar(Long id) {
        if (mesaRepository.existsById(id)) {
            mesaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
