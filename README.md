************************************************
*****CODIGO FUENTE BACKEND Y SUS ENDPOINTS******
************************************************

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

***************************************
*******ENDPOINTS DETALLADOS************
***************************************

Métodos HTTP: GET, POST, PUT y DELETE

GET:       Para recuperar datos de todas las reservaciones existentes.
POST:      Para crear nuevas reservaciones validando fecha de reservacion.
PUT:       Para actualizar reservaciones existentes.
DELETE:    Para eliminar las reservaciones.


********************
*******RUTAS********
********************

GET:                     http://localhost:8080/api/reservaciones
GET por nombre titular:  http://localhost:8080/api/reservaciones/nombre/Lorena - ("/nombre/{nombreTitularReservacion}")
POST:                    http://localhost:8080/api/reservaciones
PUT:                     http://localhost:8080/api/reservaciones/5 - ("/{id}")
DELETE:                  http://localhost:8080/api/reservaciones/fecha/2024-09-02  - ("/fecha/{fecha}")


*****************************************************
*****OPERACIONES DE GESTION DE DATOS INTEGRADAS******
*****************************************************

CRUD:       Crear, Leer, Actualizar y Eliminar datos.
Crear:      Insertar nuevas reservaciones en la base de datos.
Leer:       Consultar y recuperar reservaciones existentes.
Actualizar: Modificar reservaciones existentes.
Eliminar:   Borrar reservaciones existentes.


***********************************
*****DOCUMENTACION POSTMAN*********
***********************************

https://warped-astronaut-279843.postman.co/workspace/Heinsohn-Panas~666a932b-e0ba-415c-870a-282cc0ba6f49/documentation/26045723-8ef2eb95-5a06-4b59-96e2-af84026e1526

--------------------------------------------------------------------------------------------------------------------------
