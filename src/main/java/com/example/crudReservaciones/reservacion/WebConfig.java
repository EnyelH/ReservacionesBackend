package com.example.crudReservaciones.reservacion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración de CORS (Cross-Origin Resource Sharing) para la aplicación.
 * Permite especificar qué orígenes, métodos y cabeceras están permitidos para las solicitudes
 * cruzadas, así como la posibilidad de compartir credenciales.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura los ajustes de CORS para la aplicación.
     * @param registry Registro de configuraciones CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite configuraciones para todas las rutas de la aplicación.
                .allowedOrigins("http://localhost:3000") // Permite solicitudes desde el origen especificado.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos.
                .allowedHeaders("*") // Permite todas las cabeceras en las solicitudes.
                .allowCredentials(true); // Permite compartir credenciales (cookies, autenticaciones).
    }
}
