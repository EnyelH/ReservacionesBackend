package com.example.crudReservaciones.reservacion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador para gestionar las operaciones relacionadas con las reservaciones.
 * Proporciona endpoints para obtener, registrar, actualizar, y eliminar reservaciones.
 */
@RestController
@RequestMapping(path = "api/reservaciones")
public class ReservacionController {

    private final ReservacionService reservacionService;

    /**
     * Constructor del controlador que inyecta el servicio de reservaciones.
     * @param reservacionService Servicio de reservaciones utilizado para las operaciones CRUD.
     */
    public ReservacionController(ReservacionService reservacionService) {
        this.reservacionService = reservacionService;
    }

    /**
     * Endpoint para obtener la lista de todas las reservaciones.
     * @return Lista de reservaciones.
     */
    @GetMapping
    public List<Reservaciones> getReservaciones() {
        return reservacionService.getReservaciones();
    }

    /**
     * Endpoint para registrar una nueva reservación.
     * @param reservaciones Objeto de reservación que se quiere registrar.
     * @return Mensaje de éxito o error al registrar la reservación.
     */
    @PostMapping
    public ResponseEntity<String> registrarReservacion(@RequestBody Reservaciones reservaciones) {
        try {
            reservacionService.newReservacion(reservaciones);
            return ResponseEntity.ok("Reservación registrada exitosamente.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para actualizar una reservación existente.
     * @param id ID de la reservación a actualizar.
     * @param reservaciones Objeto de reservación con la información actualizada.
     * @return Mensaje de éxito o error al actualizar la reservación.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarReservacion(@PathVariable Long id, @RequestBody Reservaciones reservaciones) {
        try {
            reservacionService.updateReservacion(id, reservaciones);
            return ResponseEntity.ok("Reservación actualizada exitosamente.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint para consultar reservaciones por el nombre del titular.
     * @param nombreTitularReservacion Nombre del titular de la reservación.
     * @return Lista de reservaciones que coinciden con el nombre proporcionado.
     */
    @GetMapping("/nombre/{nombreTitularReservacion}")
    public List<Reservaciones> getReservacionesPorNombre(@PathVariable String nombreTitularReservacion) {
        return reservacionService.getReservacionesPorNombre(nombreTitularReservacion);
    }

    /**
     * Endpoint para eliminar una reservación por fecha.
     * @param fecha Fecha de la reservación que se quiere eliminar, en formato YYYY-MM-DD.
     * @return Mensaje de éxito o error al eliminar la reservación.
     */
    @DeleteMapping("/fecha/{fecha}")
    public ResponseEntity<String> borrarReservacion(@PathVariable String fecha) {
        try {
            LocalDate fechaReservacion = LocalDate.parse(fecha);
            reservacionService.borrarReservacion(fechaReservacion);
            return ResponseEntity.ok("Reservación eliminada exitosamente.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
