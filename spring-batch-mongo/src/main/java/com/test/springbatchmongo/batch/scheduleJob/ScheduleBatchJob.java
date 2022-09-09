package com.test.springbatchmongo.batch.scheduleJob;

import com.test.springbatchmongo.batch.config.BatchConfiguration;
import com.test.springbatchmongo.batch.util.TaskConstants;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@Component
public class ScheduleBatchJob {

    public JobLauncher jobLauncher;
    public BatchConfiguration batchConfiguration;

   @Scheduled(cron = "0 0/5 * * * *")
    public void statBatch() {
        System.out.println("batch start");
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString(TaskConstants.JOB_ID,String.valueOf(System.currentTimeMillis()));
        try {
            jobLauncher.run(batchConfiguration.job(),jobParametersBuilder.toJobParameters());
        } catch (JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException
                | JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        }
       System.out.println("batch end");
    }


}
