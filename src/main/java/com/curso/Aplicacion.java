package com.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Esto le indica a Spring que debe buscar @Configuration y @Component por dentro de mi paquete
public class Aplicacion {

    public static void main(String[] args){
        SpringApplication.run(Aplicacion.class);
    }
}
