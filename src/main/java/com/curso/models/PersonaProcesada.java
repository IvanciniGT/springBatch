package com.curso.models;

import lombok.Data;

@Data
public class PersonaProcesada {

    private Long id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String nombreCompleto;

}
