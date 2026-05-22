/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelapp;

/**
 *
 * @author salex
 */
public class Amount {
    private double precio;
    private String currency;

    public Amount(double precio, String currency) {
        this.precio = precio;
        this.currency = currency;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Amount{" + "precio=" + precio + ", currency=" + currency + '}';
    }
}
