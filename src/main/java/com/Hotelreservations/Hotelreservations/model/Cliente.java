package com.Hotelreservations.Hotelreservations.model;

import javax.persistence.*;

@Entity
@Table(name= "cliente")
public class Cliente {

    @Id
    @Column(name = "cedula_cliente")
    private Long cedula;


    @Column(name = "nombre_cliente")
    private String nombre;

    @Column(name = "apellido_cliente")
    private String apellido;

    @Column(name = "direccion_cliente")
    private String direccion;

    @Column(name = "edad_cliente")
    private int edad;

    @Column(name = "correo_electronico_cliente")
    private String correoElectronico;

    public Cliente(Long cedula, String nombre, String apellido, String direccion, int edad, String correoElectronico) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.edad = edad;
        this.correoElectronico = correoElectronico;
    }

    public Cliente() {
    }

    public Long getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getEdad() {
        return edad;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}
