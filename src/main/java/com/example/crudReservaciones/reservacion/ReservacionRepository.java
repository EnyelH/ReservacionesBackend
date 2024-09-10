package com.example.crudReservaciones.reservacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para el manejo de operaciones CRUD relacionadas con la entidad.
 * Extiende la interfaz JpaRepository de Spring Data JPA, proporcionando métodos para realizar
 * operaciones básicas sobre la base de datos.
 */
@Repository
public interface ReservacionRepository extends JpaRepository<Reservaciones, Long> {

    /**
     * Verifica si existe una reservación para la fecha especificada.
     * @param fecha La fecha de la reservación a verificar.
     * @return true si existe una reservación para la fecha, false en caso contrario.
     */
    boolean existsByFechaReservacion(LocalDate fecha);

    /**
     * Encuentra todas las reservaciones que coinciden con el nombre del titular de la reservación proporcionado.
     * @param nombreTitularReservacion El nombre del titular de la reservación para buscar.
     * @return Una lista de reservaciones que coinciden con el nombre del titular.
     */
    List<Reservaciones> findByNombreTitularReservacion(String nombreTitularReservacion);

    /**
     * Busca una reservación por la fecha de reservación.
     * @param fechaReservacion La fecha de la reservación a buscar.
     * @return Un Optional que contiene la reservación si se encuentra, o vacío si no se encuentra.
     */
    Optional<Reservaciones> findByFechaReservacion(LocalDate fechaReservacion);
}
