package com.curso.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
public class ProcesoBatch {

    @Bean
    public Job crearElJob1(Step etapa1, JobRepository repositorioJobs, JobBuilderFactory factoriaJobs){
        return factoriaJobs.get("job1")
                .repository(repositorioJobs)
                .flow(etapa1)
                .end()
                .build();
    }
}
