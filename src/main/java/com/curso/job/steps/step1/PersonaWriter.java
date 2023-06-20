package com.curso.job.steps.step1;

import com.curso.models.PersonaProcesada;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

// TODO: Paso 5
@Component
public class PersonaWriter implements ItemWriter<PersonaProcesada> {
    @Override
    public void write(List<? extends PersonaProcesada> list) throws Exception {
        list.forEach( System.out::println ); // Programación funcional
    }
}
