package com.Hotelreservations.Hotelreservations.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @Column(name = "fecha_reserva")
    private LocalDate fechaReserva;

    @ManyToOne
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "total_a_pagar")
    private double totalAPagar;




    public Reserva(Long id, LocalDate fechaReserva, Habitacion habitacion, Cliente cliente, double totalAPagar) {
        this.id = id;
        this.fechaReserva = fechaReserva;
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.totalAPagar = totalAPagar;
    }

    public Reserva() {
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getTotalAPagar() {
        return totalAPagar;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setTotalAPagar(double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }
}


