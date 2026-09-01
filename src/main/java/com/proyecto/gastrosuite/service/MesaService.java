package com.proyecto.gastrosuite.service;

import com.proyecto.gastrosuite.model.Mesa;
import com.proyecto.gastrosuite.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public List<Mesa> obtenerTodas() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> obtenerPorId(Long id) {
        return mesaRepository.findById(id);
    }

    public Mesa guardar(Mesa mesa) {
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
