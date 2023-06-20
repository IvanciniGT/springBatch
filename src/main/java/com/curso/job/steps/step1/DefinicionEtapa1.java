package com.curso.job.steps.step1;

import com.curso.models.Persona;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;



public class DefinicionEtapa1 {

    @Autowired
    PersonaProcessor procesadorDePersonas;

    @Autowired
    ItemReader<Persona> lectorPersonas;


}
