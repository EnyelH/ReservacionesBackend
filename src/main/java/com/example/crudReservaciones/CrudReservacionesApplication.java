package com.example.crudReservaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación de Spring Boot para el módulo de CRUD de reservaciones.
 * Esta clase contiene el método principal que arranca la aplicación Spring Boot.
 */
@SpringBootApplication
public class CrudReservacionesApplication {

	/**
	 * Método principal que arranca la aplicación Spring Boot.
	 * @param args Los argumentos de línea de comandos pasados al iniciar la aplicación.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CrudReservacionesApplication.class, args);
	}
}
