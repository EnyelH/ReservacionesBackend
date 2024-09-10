package com.example.crudReservaciones.reservacion;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Clase que representa una entidad de Reservación en el sistema.
 * Esta clase es utilizada para mapear las reservaciones en la base de datos mediante JPA.
 */
@Entity
@Table(name = "reservaciones")
public class Reservaciones {

    /**
     * Identificador único de la reservación.
     * Se genera automáticamente usando la estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Número de mesa asociado a la reservación.
     */
    private Long numeroMesa;

    /**
     * Nombre del titular de la reservación.
     */
    private String nombreTitularReservacion;

    /**
     * Estado de la reservación. Si es true, la reservación está activa.
     */
    private boolean reservaActiva;

    /**
     * Fecha en la que se realizó la reservación.
     * Es única para evitar duplicados en la misma fecha.
     */
    @Column(unique = true)
    private LocalDate fechaReservacion;

    /**
     * Cantidad de personas asociadas a la reservación.
     */
    private Long cantidadPersonas;

    /**
     * Servicios adicionales solicitados para la reservación (por ejemplo, catering, decoración, etc.).
     */
    private String servicios;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Reservaciones() {}

    /**
     * Constructor con todos los campos, incluyendo el ID.
     *
     * @param id Identificador único de la reservación.
     * @param numeroMesa Número de la mesa reservada.
     * @param reservaActiva Estado de la reservación (activa o no).
     * @param nombreTitularReservacion Nombre del titular de la reservación.
     * @param fechaReservacion Fecha de la reservación.
     * @param cantidadPersonas Número de personas asociadas.
     * @param servicios Servicios adicionales solicitados.
     */
    public Reservaciones(Long id, Long numeroMesa, boolean reservaActiva, String nombreTitularReservacion,
                         LocalDate fechaReservacion, Long cantidadPersonas, String servicios) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.reservaActiva = reservaActiva;
        this.nombreTitularReservacion = nombreTitularReservacion;
        this.fechaReservacion = fechaReservacion;
        this.cantidadPersonas = cantidadPersonas;
        this.servicios = servicios;
    }

    /**
     * Constructor sin el campo ID, utilizado para crear nuevas reservaciones sin un ID predefinido.
     *
     * @param numeroMesa Número de la mesa reservada.
     * @param nombreTitularReservacion Nombre del titular de la reservación.
     * @param reservaActiva Estado de la reservación (activa o no).
     * @param fechaReservacion Fecha de la reservación.
     * @param cantidadPersonas Número de personas asociadas.
     * @param servicios Servicios adicionales solicitados.
     */
    public Reservaciones(Long numeroMesa, String nombreTitularReservacion, boolean reservaActiva,
                         LocalDate fechaReservacion, Long cantidadPersonas, String servicios) {
        this.numeroMesa = numeroMesa;
        this.nombreTitularReservacion = nombreTitularReservacion;
        this.reservaActiva = reservaActiva;
        this.fechaReservacion = fechaReservacion;
        this.cantidadPersonas = cantidadPersonas;
        this.servicios = servicios;
    }

    // Métodos Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(LocalDate fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public boolean isReservaActiva() {
        return reservaActiva;
    }

    public void setReservaActiva(boolean reservaActiva) {
        this.reservaActiva = reservaActiva;
    }

    public String getNombreTitularReservacion() {
        return nombreTitularReservacion;
    }

    public void setNombreTitularReservacion(String nombreTitularReservacion) {
        this.nombreTitularReservacion = nombreTitularReservacion;
    }

    public Long getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Long numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Long getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(Long cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }
}
