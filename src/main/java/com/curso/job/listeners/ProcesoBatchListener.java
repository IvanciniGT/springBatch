package com.curso.job.listeners;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class ProcesoBatchListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Me ejecuto cuando empieza el job");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("Me ejecuto cuando acaba el job");
    }
}
