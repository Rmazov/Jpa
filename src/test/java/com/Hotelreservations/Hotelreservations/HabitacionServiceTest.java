package com.Hotelreservations.Hotelreservations;

import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.repository.ClienteRepository;
import com.Hotelreservations.Hotelreservations.repository.HabitacionRepository;
import com.Hotelreservations.Hotelreservations.repository.ReservaRepository;
import com.Hotelreservations.Hotelreservations.service.HabitacionService;
import com.Hotelreservations.Hotelreservations.service.ReservaService;
import org.junit.Before;
import com.Hotelreservations.Hotelreservations.model.TipoHabitacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
@RunWith(MockitoJUnitRunner.class)
public class HabitacionServiceTest {
    @Mock
    private HabitacionRepository habitacionRepositoryMock;

    private HabitacionService habitacionService;

    @Before
    public void setUp() {

        habitacionService = new HabitacionService(habitacionRepositoryMock);
    }


    @Test
    public void testCrearHabitacion() {
        TipoHabitacion tipo = TipoHabitacion.PREMIUM;
        double precioBase = -100.0;
        Habitacion habitacion = new Habitacion(4544L, tipo, precioBase);

        when(habitacionRepositoryMock.save(habitacion)).thenReturn(habitacion);

        System.out.println(habitacion);
        Habitacion habitacionCreada = habitacionService.crearHabitacion(habitacion);

        assertNotNull(habitacionCreada);
        assertNotNull(habitacionCreada.getId());
        assertEquals(habitacionCreada.getTipo(), tipo);
        assertEquals(habitacionCreada.getPrecioBase(), precioBase, 0.0);

        verify(habitacionRepositoryMock, times(1)).save(habitacion);
    }



}
