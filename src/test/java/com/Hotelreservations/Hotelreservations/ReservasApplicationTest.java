package com.Hotelreservations.Hotelreservations;

import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import com.Hotelreservations.Hotelreservations.model.TipoHabitacion;
import com.Hotelreservations.Hotelreservations.repository.ClienteRepository;
import com.Hotelreservations.Hotelreservations.repository.HabitacionRepository;
import com.Hotelreservations.Hotelreservations.repository.ReservaRepository;
import com.Hotelreservations.Hotelreservations.service.HabitacionService;
import com.Hotelreservations.Hotelreservations.service.ReservaService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
public class ReservasApplicationTest {
    @Mock
    private HabitacionRepository habitacionRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ReservaRepository reservaRepository;

    @Test
    public void testGenerar() {

        // Mock de la habitación
        Habitacion habitacionMock = new Habitacion(1L, TipoHabitacion.PREMIUM, 100.0);
        when(habitacionRepository.findById(1L)).thenReturn(Optional.of(habitacionMock));
        when(habitacionRepository.save(habitacionMock)).thenReturn(habitacionMock);


        // Mock del cliente
        Cliente clienteMock = new Cliente(12345L, "Juan", "Pérez", "Calle 123", 30, "juanperez@example.com");
        when(clienteRepository.findById(12345L)).thenReturn(Optional.of(clienteMock));

        // Fecha de reserva
        String fecha = "2023-04-10";

        // Crea la reserva
        ReservaService reservaService = new ReservaService(clienteRepository,habitacionRepository,reservaRepository);
        Reserva reservaCreada = reservaService.generar(fecha, 1L, 12345L);

        // Verifica que la reserva fue creada correctamente
        assertNotNull(reservaCreada);
        assertEquals(habitacionMock, reservaCreada.getHabitacion());
        assertEquals(clienteMock, reservaCreada.getCliente());
        assertEquals(LocalDate.parse(fecha), reservaCreada.getFechaReserva());
        assertEquals(100.0, reservaCreada.getTotalAPagar());

        // Verifica que se llamó al método save del repository de reservas
        verify(reservaRepository, times(1)).save(reservaCreada);

    }

}
