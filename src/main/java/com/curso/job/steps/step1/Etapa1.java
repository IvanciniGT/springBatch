package com.curso.job.steps.step1;

import lombok.Getter;
import org.springframework.batch.core.Step;

public class Etapa1 {

    @Getter
    private Step step;
    public Etapa1(Step step){
        this.step=step;
    }

}
