package com.proyecto.gastrosuite.service;

import com.proyecto.gastrosuite.model.Mesa;
import com.proyecto.gastrosuite.model.Reserva;
import com.proyecto.gastrosuite.model.Usuario;
import com.proyecto.gastrosuite.repository.MesaRepository;
import com.proyecto.gastrosuite.repository.ReservaRepository;
import com.proyecto.gastrosuite.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final MesaRepository mesaRepository;

    public ReservaService(ReservaRepository reservaRepository, UsuarioRepository usuarioRepository, MesaRepository mesaRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
        this.mesaRepository = mesaRepository;
    }

    public List<Reserva> obtenerTodas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> obtenerPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva guardar(Reserva reserva) {
        if (reserva.getUsuario() != null && reserva.getUsuario().getIdUsuario() != null) {
            Usuario u = usuarioRepository.findById(reserva.getUsuario().getIdUsuario()).orElse(reserva.getUsuario());
            reserva.setUsuario(u);
        }
        if (reserva.getMesa() != null && reserva.getMesa().getIdMesa() != null) {
            Mesa m = mesaRepository.findById(reserva.getMesa().getIdMesa()).orElse(reserva.getMesa());
            reserva.setMesa(m);
        }
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
