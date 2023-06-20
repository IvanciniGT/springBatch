package com.curso.job.steps.step1.listeners;

import com.curso.models.Persona;
import com.curso.models.PersonaProcesada;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Etapa1WriterListener implements ItemWriteListener<PersonaProcesada> {

    @Override
    public void beforeWrite(List<? extends PersonaProcesada> list) {
        System.out.println("Antes de escribir : "+list);

    }

    @Override
    public void afterWrite(List<? extends PersonaProcesada> list) {
        System.out.println("Despues de escribir : "+list);

    }

    @Override
    public void onWriteError(Exception e, List<? extends PersonaProcesada> list) {
        System.out.println("error al escribir : "+list);

    }
}
