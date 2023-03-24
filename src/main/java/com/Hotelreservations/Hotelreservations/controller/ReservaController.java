package com.Hotelreservations.Hotelreservations.controller;

import com.Hotelreservations.Hotelreservations.model.Habitacion;
import com.Hotelreservations.Hotelreservations.model.Reserva;
import com.Hotelreservations.Hotelreservations.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("api/v1")
public class ReservaController {
@Autowired
private ReservaService reservaService;
    @PostMapping("/reserva")
    public ResponseEntity<Reserva> crearReserva(@RequestParam int numero, @RequestParam long cedula,@RequestParam LocalDate fechaReserva) {
        Reserva reserva = ReservaService.generar(numero, cedula, fechaReserva);
        return ResponseEntity.ok(reserva);
    }



}
