/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.hotelapp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author salex
 */
public class HotelApp {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Habitacion> habitaciones = new ArrayList<>();
    static ArrayList<Reserva> reservas = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Dime tu nombre:");
        String nombre = sc.nextLine();
        System.out.println("Dime tu apellio:");
        String apellido = sc.nextLine();
        System.out.println("Dime tu puesto (Recepcion, limpieza, direccion):");
        String puesto = sc.nextLine();

        Empleado empleado = new Empleado(nombre, apellido, puesto);

        System.out.println("Bienvenido/a " + empleado.getNombre() + " " + empleado.getApellido());

        int opc;

        do {
            opc = menu();

            switch (opc) {
                case 1:
                    darAltaCliente();
                    break;
                case 2:
                    darAltaHabitacion();
                    break;
                case 3:
                    crearReserva();
                    break;
                case 4:
                    anadirServicioReserva();
                    break;
                case 5:
                    mostrarFactura();
                    break;
                case 6:
                    cancelarReserva();
                    break;
                case 7:
                    mostrarHabitaciones();
                    break;
                case 8:
                    mostrarReservas();
                    break;
                case 9:
                    System.out.println("Saliendo del programa...");
                    break;
            }

        } while (opc != 9);
    }

    public static int menu() {

        System.out.println("Bienvenido al programa HotelApp: ");
        System.out.println("1. Dar de alta cliente");
        System.out.println("2. Dar de alta a una habitacion");
        System.out.println("3. Crear reserva");
        System.out.println("4. Añadir servicios reserva");
        System.out.println("5. Mostrar factura reserva");
        System.out.println("6. Cancelar reserva");
        System.out.println("7. Mostrar Habitaciones");
        System.out.println("8. Mostrar Reservas");
        System.out.println("9. Salir del programa");
        System.out.println("Elige una opcion:");

        int opc = sc.nextInt();
        sc.nextLine();

        return opc;

    }

    public static void darAltaCliente() {
        Cliente cli = null;

        System.out.println("Dime el nombre del cliente: ");
        String nombre = sc.nextLine();

        System.out.println("Dime el apellido del cliente: ");
        String apellido = sc.nextLine();

        System.out.println("Dime el DNI del cliente: ");
        String dni = sc.nextLine();

        System.out.println("Dime el telefono del cliente: ");
        String telf = sc.nextLine();

        for (Cliente cliente : clientes) {
            if (cliente.getDni().equalsIgnoreCase(dni)) {
                cli = cliente;
                break;
            }
        }

        if (cli != null) {
            System.out.println("Cliente con DNI " + dni + "ya existe.");
        } else {
            Cliente cliente = new Cliente(dni, telf, nombre, apellido);
            clientes.add(cliente);
            System.out.println("Cliente dado de alta.");
        }

    }

    public static void darAltaHabitacion() {
        Habitacion hab = null;

        System.out.println("Dime el numero de la habitacion:");
        int num = sc.nextInt();
        sc.nextLine();

        System.out.println("Dime el tipo de habitacion (individual, doble, suite):");
        String tipo = sc.nextLine();

        System.out.println("Establece el precio de la habitacion por noche: ");
        double valorPrecio = sc.nextDouble();
        sc.nextLine();

        System.out.println("Establece la moneda: ");
        String moneda = sc.nextLine();

        Amount precio = new Amount(valorPrecio, moneda);

        Habitacion habitacion = new Habitacion(num, tipo, precio, true);

        if (habitaciones.contains(habitacion)) {
            System.out.println("Habitacion" + num + "ya existe");
        } else {
            habitaciones.add(habitacion);
            System.out.println("Habitacion dada de alta.");
        }

    }

    public static void crearReserva() {
        Cliente cli = null;
        Habitacion hab = null;

        System.out.println("Dime el DNI del cliente:");
        String dni = sc.nextLine();
        for (Cliente cliente : clientes) {
            if (cliente.getDni().equalsIgnoreCase(dni)) {
                cli = cliente;
                break;
            }
        }

        System.out.println("Dime el numero de la habitacion: ");
        int num = sc.nextInt();
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == num) {
                hab = habitacion;
                break;
            }
        }

        System.out.println("Dime la fecha de entrada...");
        System.out.println("Dia:");
        int day = sc.nextInt();
        System.out.println("Mes:");
        int month = sc.nextInt();
        System.out.println("Año:");
        int year = sc.nextInt();
        sc.nextLine();
        Fecha entrada = new Fecha(day, month, year);

        System.out.println("Dime la fecha de salida...");
        System.out.println("Dia:");
        int day2 = sc.nextInt();
        System.out.println("Mes:");
        int month2 = sc.nextInt();
        System.out.println("Año:");
        int year2 = sc.nextInt();
        sc.nextLine();
        Fecha salida = new Fecha(day2, month2, year2);

        int noches = (year2 * 365 + month2 * 30 + day2) - (year * 365 + month * 30 + day);
        double importe = noches * hab.getPrecio().getPrecio();

        if (cli == null) {
            System.out.println("No existe ningun cliente con DNI" + dni);
            return;
        }
        if (hab == null) {
            System.out.println("No existe ninguna habitacion con numero" + num);
            return;
        }
        if (!hab.isDisponible()) {
            System.out.println("Habitacion con numero" + num + "no disponible");
            return;
        } else {
            Reserva nuevaReserva = new Reserva(cli, hab, entrada, salida, importe);
            reservas.add(nuevaReserva);
            hab.setDisponible(false);
            System.out.println("Reserva creada por " + noches + " noches y con un coste total de " + importe + "€");
        }

    }

    public static void anadirServicioReserva() {
        Reserva resv = null;

        System.out.println("Dime el DNI:");
        String dni = sc.nextLine();

        for (Reserva reserva : reservas) {
            if (reserva.getCliente().getDni().equalsIgnoreCase(dni)) {
                resv = reserva;
                break;
            }
        }

        if (resv == null) {
            System.out.println("No existe ninguna reserva con DNI " + dni);
        } else {
            System.out.println("Dime el nombre del servicio:");
            String nombre = sc.nextLine();
            System.out.println("Precio del servicio:");
            double precio = sc.nextDouble();
            sc.nextLine();
            System.out.println("¿Servicio premium?(1 = si | 2 = no)");
            int opcion = sc.nextInt();

            if (opcion == 1) {
                Servicio servicio = new Servicio(nombre, precio, true);
                resv.getServicios().add(servicio);
            }
            if (opcion == 2) {
                Servicio servicio = new Servicio(nombre, precio, false);
                resv.getServicios().add(servicio);
            } else {
                System.out.println("Opcion incorrecta...");
            }

        }
    }

    public static void mostrarFactura() {
        Reserva resv = null;

        System.out.println("Dime el DNI:");
        String dni = sc.nextLine();

        for (Reserva reserva : reservas) {
            if (reserva.getCliente().getDni().equalsIgnoreCase(dni)) {
                resv = reserva;
                break;
            }
        }

        if (resv == null) {
            System.out.println("No existe ninguna reserva con DNI " + dni);
        }
        double total = resv.getImporte();

        if (resv.getServicios() != null && !resv.getServicios().isEmpty()) {
            for (Servicio servicio : resv.getServicios()) {
                total += servicio.getPrecio();
            }
        }

        System.out.println("Precio total: " + total + "€");
    }

    public static void cancelarReserva() {
        Reserva resv = null;

        System.out.println("Dime el DNI:");
        String dni = sc.nextLine();

        for (Reserva reserva : reservas) {
            if (reserva.getCliente().getDni().equalsIgnoreCase(dni)) {
                resv = reserva;
                break;
            }
        }

        if (resv == null) {
            System.out.println("No existe ninguna reserva con DNI " + dni);
        } else {
            resv.getHabitacion().setDisponible(true);
            reservas.remove(resv);
            System.out.println("Reserva cancelada...");
        }
    }

    private static void mostrarHabitaciones() {

        if (habitaciones.isEmpty()) {
            System.out.println("Ninguna habitacion registrada");
            return;

        }
        for (Habitacion habitacion : habitaciones) {
            System.out.println(habitacion.toString());
        }
    }

    private static void mostrarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Ninguna reserva hecha");
            return;
        }

        for (Reserva reserva : reservas) {
            System.out.println(reserva.toString());
        }
    }
}
