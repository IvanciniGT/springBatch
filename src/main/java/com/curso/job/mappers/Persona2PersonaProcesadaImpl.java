package com.curso.job.mappers;

import com.curso.models.Persona;
import com.curso.models.PersonaProcesada;
import org.springframework.stereotype.Component;

@Component
public class Persona2PersonaProcesadaImpl implements Persona2PersonaProcesada {
    @Override
    public PersonaProcesada persona2personaProcesada(Persona persona) {
        PersonaProcesada personaProcesada = new PersonaProcesada();
        personaProcesada.setNombre(persona.getNombre());
        personaProcesada.setApellidos(persona.getApellidos());
        personaProcesada.setDni(persona.getDni());
        personaProcesada.setEmail(persona.getEmail());
        personaProcesada.setNombreCompleto(persona.getNombre() + " " + persona.getApellidos());
        return personaProcesada;
    }
}
// TODO : Esto es una mierda gigante
// Necesito una interfaz de esta clase