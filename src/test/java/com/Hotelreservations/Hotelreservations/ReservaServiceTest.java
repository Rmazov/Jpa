package com.Hotelreservations.Hotelreservations;

import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import com.Hotelreservations.Hotelreservations.repository.ClienteRepository;
import com.Hotelreservations.Hotelreservations.repository.HabitacionRepository;
import com.Hotelreservations.Hotelreservations.repository.ReservaRepository;
import com.Hotelreservations.Hotelreservations.service.ClienteService;
import com.Hotelreservations.Hotelreservations.service.ReservaService;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ReservaServiceTest {

    private ReservaRepository reservaRepository;
    private HabitacionRepository habitacionRepository;

    private ClienteRepository clienteRepository;

    private ReservaService reservaService;
    private ClienteService clienteService ;

    @Before
    public void setUp(){
        this.reservaRepository = mock(ReservaRepository.class);
        this.habitacionRepository = mock(HabitacionRepository.class);
        this.clienteRepository = mock(ClienteRepository.class);
        this.reservaService = new ReservaService(clienteRepository, habitacionRepository, reservaRepository);
    }

  @Test
  public void generar(){
      //Arrange

  }


    @Test
    public void probarSumaConPrimerNumeroNegativo(){
        //Arrange
        Integer numero1 = -1;
        Integer numero2 = 2;
        //act
        Integer suma = this.reservaService.sumar(numero1, numero2);
        //assert
        assertTrue(suma == 0);
    }

}



