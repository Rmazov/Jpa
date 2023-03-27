package com.Hotelreservations.Hotelreservations.service;

import com.Hotelreservations.Hotelreservations.exception.ApiRequestException;
import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import com.Hotelreservations.Hotelreservations.repository.ClienteRepository;
import com.Hotelreservations.Hotelreservations.repository.HabitacionRepository;
import com.Hotelreservations.Hotelreservations.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

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


    public boolean validarIdHabitacion(List<Habitacion> disponibles,long id){


        boolean habitacionDisponible = false;

        for (Habitacion habitacion : disponibles) {
            if (habitacion.getId() == id) {
                habitacionDisponible = true;
                break;
            }
        }

        if (!habitacionDisponible) {
            // La habitación con el id especificado no está disponible
            return false; // o lanzar una excepción, según corresponda en tu caso
        }
        return habitacionDisponible;
    }




    public Reserva generar(String fecha, long id, long cedula) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha, formatter);


         Optional<Habitacion> habitacion = habitacionRepository.findById(id);
        Optional<Cliente> cliente = clienteRepository.findById(cedula);
        // validacion que existen habitacion y cliente enviados para la reserva
        double totalPagar=0;
        double descuento=0;
        if (habitacion.isPresent() && cliente.isPresent()) {

            List<Habitacion> disponibles = (validarDisponibilidadFecha(fecha));
            boolean habitacionValido =validarIdHabitacion(disponibles, id);
                if(habitacionValido){

                Habitacion habitacionReservada = habitacion.get();
                Cliente clienteQueReserva = cliente.get();
                String tipo = String.valueOf(habitacionReservada.getTipo());
                if(tipo.equalsIgnoreCase("ESTANDAR")){
                     totalPagar = habitacion.get().getPrecioBase();
                }else {
                     descuento = (habitacion.get().getPrecioBase()) * 0.05;
                     totalPagar = habitacion.get().getPrecioBase()- descuento;
                }

                Reserva reserva1 = new Reserva(habitacionReservada,clienteQueReserva,date,totalPagar);
                    return this.reservaRepository.save(reserva1);

                }else {
                    throw new ApiRequestException("habitacion no disponible para esa fecha");

                }

        }else {
            throw new ApiRequestException("cliente o habitacion nulas");
        }

   }


    public List<Habitacion> validarDisponibilidadFechaPremiun(String fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha, formatter);
        List<Habitacion> disponiblesPremiun = new ArrayList<>();

        List<Habitacion> disponibles = (validarDisponibilidadFecha(fecha));


        for (Habitacion habitacion : disponibles) {

            String tipo = String.valueOf(habitacion.getTipo());
            if(tipo.equalsIgnoreCase("PREMIUM")){
                disponiblesPremiun.add(habitacion);

            }
        }



        return disponiblesPremiun;
    }

    public List<Habitacion> validarDisponibilidadFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha, formatter);
        List<Habitacion> disponibles = new ArrayList<>();

        // Obtener todas las habitaciones
        List<Habitacion> habitaciones = (List<Habitacion>) habitacionRepository.findAll();

        // Obtener las habitaciones reservadas en la fecha especificada
        List<Habitacion> habitacionesReservadas = reservaRepository.findByFechaReserva(date)
                .stream()
                .map(Reserva::getHabitacion)
                .collect(Collectors.toList());

        // Filtrar las habitaciones disponibles
        for (Habitacion habitacion : habitaciones) {
            if (!habitacionesReservadas.contains(habitacion)) {
                disponibles.add(habitacion);
            }
        }

        return disponibles;
    }


    public List<Reserva> verReservasCliente(Long cedula) {

        List<Reserva> listaDeTodasLasReservas = (List<Reserva>) reservaRepository.findAll();
        List<Reserva> listaReservasId = new ArrayList<>();
        for (Reserva reserva : listaDeTodasLasReservas) {
            Cliente clienteDeReserva = reserva.getCliente();
            if (clienteDeReserva.getCedula() == cedula) {
                listaReservasId.add(reserva);

            }
        }
        return listaReservasId;

    }
}
