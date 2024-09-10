package com.example.crudReservaciones.reservacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar las operaciones relacionadas con las reservaciones.
 * Proporciona métodos para obtener, registrar, actualizar y eliminar reservaciones.
 */
@Service
public class ReservacionService {

    private final ReservacionRepository reservacionRepository;

    /**
     * Constructor del servicio que inyecta el repositorio de reservaciones.
     * @param reservacionRepository Repositorio de reservaciones utilizado para las operaciones CRUD.
     */
    @Autowired
    public ReservacionService(ReservacionRepository reservacionRepository) {
        this.reservacionRepository = reservacionRepository;
    }

    /**
     * Obtiene la lista de todas las reservaciones.
     * @return Lista de reservaciones.
     */
    public List<Reservaciones> getReservaciones() {
        return this.reservacionRepository.findAll();
    }

    /**
     * Obtiene las reservaciones filtradas por el nombre del titular.
     * @param nombreTitularReservacion Nombre del titular de la reservación.
     * @return Lista de reservaciones que coinciden con el nombre proporcionado.
     */
    public List<Reservaciones> getReservacionesPorNombre(String nombreTitularReservacion) {
        return reservacionRepository.findByNombreTitularReservacion(nombreTitularReservacion);
    }

    /**
     * Registra una nueva reservación en el repositorio.
     * Verifica si ya existe una reservación para la fecha especificada.
     * Si existe, lanza una excepción  y retorna false.
     * Si no existe, guarda la nueva reservación y retorna true.
     *
     * @param reservaciones El objeto que contiene los detalles de la reservación a registrar.
     * @return true si la reservación se ha registrado exitosamente, false si ya existía una reservación para la misma fecha.
     * @throws IllegalStateException si ya existe una reservación para la fecha proporcionada.
     */
    public boolean newReservacion(Reservaciones reservaciones) {
        if (reservacionRepository.existsByFechaReservacion(reservaciones.getFechaReservacion())) {
            throw new IllegalStateException("Ya existe una reservación para esa fecha.");
        }
        reservacionRepository.save(reservaciones);
        return true;
    }

    /**
     * Verifica si existe una reservación para una fecha específica.
     * @param fecha Fecha de la reservación.
     * @return Verdadero si existe una reservación para la fecha, falso en caso contrario.
     */
    public boolean existsByFecha(LocalDate fecha) {
        return reservacionRepository.existsByFechaReservacion(fecha);
    }

    /**
     * Actualiza una reservación existente.
     * @param id ID de la reservación a actualizar.
     * @param updatedReservacion Objeto de reservación con la información actualizada.
     * @return La reservación actualizada.
     * @throws IllegalStateException Si la reservación con el ID especificado no se encuentra o si la nueva fecha ya está reservada.
     */
    public Reservaciones updateReservacion(Long id, Reservaciones updatedReservacion) {
        Optional<Reservaciones> existingReservacionOptional = reservacionRepository.findById(id);
        if (existingReservacionOptional.isEmpty()) {
            throw new IllegalStateException("Reservación con ID " + id + " no encontrada.");
        }

        Reservaciones existingReservacion = existingReservacionOptional.get();
        // Actualiza los campos según el objeto updatedReservacion
        existingReservacion.setNumeroMesa(updatedReservacion.getNumeroMesa());
        existingReservacion.setNombreTitularReservacion(updatedReservacion.getNombreTitularReservacion());
        existingReservacion.setReservaActiva(updatedReservacion.isReservaActiva());
        existingReservacion.setFechaReservacion(updatedReservacion.getFechaReservacion());
        existingReservacion.setCantidadPersonas(updatedReservacion.getCantidadPersonas());

        // Verifica si la nueva fecha ya está reservada
        if (reservacionRepository.existsByFechaReservacion(updatedReservacion.getFechaReservacion())) {
            throw new IllegalStateException("Ya existe una reservación para la nueva fecha.");
        }

        return reservacionRepository.save(existingReservacion);
    }

    /**
     * Borra una reservación por fecha.
     * @param fecha Fecha de la reservación a eliminar.
     * @throws IllegalStateException Si no se encuentra ninguna reservación para la fecha especificada.
     */
    public void borrarReservacion(LocalDate fecha) {
        Optional<Reservaciones> reservacion = reservacionRepository.findByFechaReservacion(fecha);

        if (reservacion.isPresent()) {
            reservacionRepository.delete(reservacion.get());
        } else {
            throw new IllegalStateException("No se encontró ninguna reservación para la fecha especificada.");
        }
    }
}
