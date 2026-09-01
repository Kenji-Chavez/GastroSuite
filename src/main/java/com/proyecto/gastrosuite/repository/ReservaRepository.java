package com.proyecto.gastrosuite.repository;

import com.proyecto.gastrosuite.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByUsuarioIdUsuario(Long idUsuario);

    List<Reserva> findByMesaIdMesa(Long idMesa);

    List<Reserva> findByEstado(Boolean estado);

    List<Reserva> findByMesaIdMesaAndFechaReservaAndEstado(Long idMesa, LocalDateTime fechaReserva, Boolean estado);
}
