package com.proyecto.gastrosuite.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.proyecto.gastrosuite.model.Restaurante;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RestauranteRepositoryTest {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Test
    void debeGuardarYBuscarRestaurante() {

        Restaurante restaurante = new Restaurante(
                "Polleria El Chino",
                "Av. Peru 123",
                "987654321",
                true
        );

        Restaurante guardado =
                restauranteRepository.save(restaurante);

        assertThat(guardado.getIdRestaurante()).isNotNull();

        Optional<Restaurante> resultado =
                restauranteRepository.findById(
                        guardado.getIdRestaurante()
                );

        assertThat(resultado).isPresent();

        assertThat(resultado.get().getNombre())
                .isEqualTo("Polleria El Chino");
    }
}