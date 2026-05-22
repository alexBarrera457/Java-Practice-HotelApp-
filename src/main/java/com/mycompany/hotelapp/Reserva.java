/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelapp;

import java.util.ArrayList;

/**
 *
 * @author salex
 */
public class Reserva {

    private Cliente cliente;
    private Habitacion habitacion;
    private Fecha entrada;
    private Fecha salida;
    private double importe;
    private ArrayList<Servicio> servicios = new ArrayList<>();

    public Reserva(Cliente cliente, Habitacion habitacion, Fecha entrada, Fecha salida, double importe) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.entrada = entrada;
        this.salida = salida;
        this.importe = importe;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Fecha getEntrada() {
        return entrada;
    }

    public void setEntrada(Fecha entrada) {
        this.entrada = entrada;
    }

    public Fecha getSalida() {
        return salida;
    }

    public void setSalida(Fecha salida) {
        this.salida = salida;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    @Override
    public String toString() {
        return "Reserva{" + "cliente=" + cliente + ", habitacion=" + habitacion + ", entrada=" + entrada + ", salida=" + salida + ", importe=" + importe + ", servicios=" + servicios + '}';
    }

}
