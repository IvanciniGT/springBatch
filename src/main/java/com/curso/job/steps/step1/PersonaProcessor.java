package com.curso.job.steps.step1;

import com.curso.models.Persona;
import com.curso.models.PersonaProcesada;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

// TODO: PASO 3
// Montar el procesador de Personas, para validarlas, transformarlas...
@Component
    // ^^ Spring!!!!
    // Ésto es un componente de mi aplicación, que yo quiero que pueda ser usado por
    // Otros componentes
    // PERO!!!!!! No quiero que otros componentes dependan de éste... Si éste el día de mañana lo cambio
    // Quiero que ese cambio sea transparente para el resto de componentes
public class PersonaProcessor implements ItemProcessor<Persona, PersonaProcesada> {
    @Override
    public PersonaProcesada process(Persona persona) throws Exception {
        // Validar los datos de la persona
        if (!validarEmail(persona.getEmail())) return null;
        // Filtrar personas para que no se guarden allá donde sea
        if(persona.getDni().endsWith("A")) return null;
        // Calcular datos de la persona
        PersonaProcesada personaProcesada = new PersonaProcesada();
        personaProcesada.setNombre(persona.getNombre());
        personaProcesada.setApellidos(persona.getApellidos());
        personaProcesada.setDni(persona.getDni());
        personaProcesada.setEmail(persona.getEmail());
        personaProcesada.setNombreCompleto(persona.getNombre() + " " + persona.getApellidos());

        return null;
    }

    public static boolean validarEmail(String email){
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
