package com.proyecto.gastrosuite.repository;

import com.proyecto.gastrosuite.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {

    List<Mesa> findByRestauranteIdRestaurante(Long idRestaurante);

    List<Mesa> findByRestauranteIdRestauranteAndEstado(Long idRestaurante, Boolean estado);
}
