package com.Hotelreservations.Hotelreservations.model;

import javax.persistence.*;



@Entity
@Table(name = "habitaciones")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habitacion")
    private Long id;

    @Column(name = "numero_habitacion")
    private int numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_habitacion")
    private TipoHabitacion tipo;

    @Column(name = "precio_base")
    private double precioBase;



    public Habitacion(Long id, int numero, TipoHabitacion tipo, double precioBase) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.precioBase = precioBase;
    }

    public Habitacion() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public Long getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public double getPrecioBase() {
        return precioBase;
    }
}
