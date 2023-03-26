package com.Hotelreservations.Hotelreservations.service;

import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.TipoHabitacion;
import com.Hotelreservations.Hotelreservations.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente crear(Cliente cliente) {
       this.clienteRepository.save(cliente);
        return cliente;
    }


    public void crearClientes() {
       this.clienteRepository.save(new Cliente(121L,"rICA","MAZ","cRA 40 45",21,"example@hotmail.com"));

        this.clienteRepository.save(new Cliente(12661L,"ESTE","MAZ","cRA 40 45",21,"example@hotmail.com"));

    }
}
