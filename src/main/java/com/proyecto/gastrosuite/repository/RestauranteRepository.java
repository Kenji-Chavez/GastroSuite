package com.proyecto.gastrosuite.repository;

import com.proyecto.gastrosuite.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    List<Restaurante> findByEstado(Boolean estado);

    List<Restaurante> findByNombreContainingIgnoreCase(String nombre);
}
