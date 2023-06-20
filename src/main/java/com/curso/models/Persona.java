package com.curso.models;

import lombok.Data;

@Data
// Getter y Setter en todos los campos privados
// Método toString
// Método equals y hashcode
public class Persona {
    // TODO: PASO 1
    // Esto es lo que cargo desde el EXCEL
    // Esta clase tiene que tener estructura de POJO
    // AQUI NO DEBE HABER LOGICA DE NINGUN TIPO. Es un objeto de TRANSPORTE DE DATOS.

    private String nombre;
    private String apellidos;
    private String dni;
    private String email;

}
