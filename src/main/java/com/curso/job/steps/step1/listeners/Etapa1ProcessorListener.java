package com.curso.job.steps.step1.listeners;

import com.curso.models.Persona;
import com.curso.models.PersonaProcesada;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Component
public class Etapa1ProcessorListener implements ItemProcessListener<Persona, PersonaProcesada> {


    @Override
    public void beforeProcess(Persona persona) {
        System.out.println("Empiezo con : "+persona);
    }

    @Override
    public void afterProcess(Persona persona, PersonaProcesada personaProcesada) {
        System.out.println("Acabo con : "+persona);
    }

    @Override
    public void onProcessError(Persona persona, Exception e) {
        System.out.println("Error con : "+persona);
        e.printStackTrace();

    }
}
