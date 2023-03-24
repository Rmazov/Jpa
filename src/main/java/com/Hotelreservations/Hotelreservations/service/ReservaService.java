package com.Hotelreservations.Hotelreservations.service;

import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import com.Hotelreservations.Hotelreservations.repository.ClienteRepository;
import com.Hotelreservations.Hotelreservations.repository.HabitacionRepository;
import com.Hotelreservations.Hotelreservations.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservaService {

private ClienteRepository clienteRepository;
private HabitacionRepository habitacionRepository;

private ReservaRepository reservaRepository;
    @Autowired
    public ReservaService(ClienteRepository clienteRepository, HabitacionRepository habitacionRepository, ReservaRepository reservaRepository) {
        this.clienteRepository = clienteRepository;
        this.habitacionRepository = habitacionRepository;
        this.reservaRepository = reservaRepository;
    }

    public Reserva generar(int numero, long cedula, LocalDate fechaReserva){
        if(numero < 0  || cedula <0 ) {
            throw new RuntimeException("cliente o habitacion NULOS");
        }

        Optional<Cliente> cliente =  this.clienteRepository.findById((long) numero);
        Optional<Habitacion> habitacion =  this.habitacionRepository.findById(cedula);

        if(cliente.isPresent() && habitacion.isPresent()){
            Reserva reserva1 = new Reserva(long 2,fechaReserva,numero,cedula,long 454 );
            this.reservaRepository.save(reserva1);
            return reserva1;
        }
        return new Reserva();
    }
}
