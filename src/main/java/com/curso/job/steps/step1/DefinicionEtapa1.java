package com.curso.job.steps.step1;

import com.curso.models.Persona;
import com.curso.models.PersonaProcesada;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefinicionEtapa1 {

    private StepBuilderFactory factoriaEtapas;

    public DefinicionEtapa1(StepBuilderFactory factoriaEtapas){
        this.factoriaEtapas = factoriaEtapas;
    }

    @Bean
    public Step crearEtapa1(ItemReader<Persona> lectorPersonas, PersonaProcessor procesadorDePersonas, PersonaWriter personaWriter){
        return this.factoriaEtapas.get("etapa1")
                .<Persona, PersonaProcesada>chunk(10) // Lee tantas personas para procesarlas de una atacada
                .reader(lectorPersonas)
                .processor(procesadorDePersonas)
                .writer(personaWriter)
                .build();
    }
}
