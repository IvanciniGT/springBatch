package com.curso.job.schedules;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ProgramacionDelProceso {

    private JobLauncher lanzadorDeProcesos;
    private Job miProceso;
    public ProgramacionDelProceso(JobLauncher lanzadorDeProcesos, Job miProceso){
        this.lanzadorDeProcesos=lanzadorDeProcesos;
        this.miProceso=miProceso;
    }

    @Scheduled( fixedRate = 2000 ) // Cada minuto // */30 */6 */10 1-11/2 1-5 *
    public void programarElProceso() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters parametros = new JobParametersBuilder()
                .toJobParameters();
        lanzadorDeProcesos.run(miProceso, parametros);
    }

}
