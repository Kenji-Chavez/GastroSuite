package com.proyecto.gastrosuite.service;

import com.proyecto.gastrosuite.model.Restaurante;
import com.proyecto.gastrosuite.repository.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestauranteServiceTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private RestauranteService restauranteService;

    @Test
    void obtenerPorIdTest() {
        // Se simula un restaurante en el repositorio mock
        Restaurante restauranteMock = new Restaurante("Polleria El Chino", "Av. Peru 123", "987654321", true);
        restauranteMock.setIdRestaurante(1L);

        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(restauranteMock));

        // Se llama al método del servicio
        Optional<Restaurante> resultado = restauranteService.obtenerPorId(1L);

        // Se verifica la respuesta esperada
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombre()).isEqualTo("Polleria El Chino");
        verify(restauranteRepository, times(1)).findById(1L);
    }
}
