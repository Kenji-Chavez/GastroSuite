package com.proyecto.gastrosuite.service;

import com.proyecto.gastrosuite.model.Reserva;
import com.proyecto.gastrosuite.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> obtenerTodas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> obtenerPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva guardar(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> actualizar(Long id, Reserva reservaDetalles) {
        return reservaRepository.findById(id).map(reserva -> {
            reserva.setFechaReserva(reservaDetalles.getFechaReserva());
            reserva.setCantidadPersonas(reservaDetalles.getCantidadPersonas());
            reserva.setEstado(reservaDetalles.getEstado());
            if (reservaDetalles.getMesa() != null) {
                reserva.setMesa(reservaDetalles.getMesa());
            }
            if (reservaDetalles.getUsuario() != null) {
                reserva.setUsuario(reservaDetalles.getUsuario());
            }
            return reservaRepository.save(reserva);
        });
    }

    public boolean eliminar(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
