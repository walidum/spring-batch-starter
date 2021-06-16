package com.meylium.batch.config;

import com.meylium.batch.listener.JobCompletionListener;
import com.meylium.batch.step.Processor;
import com.meylium.batch.step.Reader;
import com.meylium.batch.step.Reader2;
import com.meylium.batch.step.Writer;
import com.meylium.batch.tasklet.FileDeletingTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {
    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job Job1() {
        return jobBuilderFactory.get("Job1")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(Step1())
                .end().build();
    }

    @Bean
    public Job Job2() {
        return jobBuilderFactory.get("Job2")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .start(Step2())
                .build();
    }

    @Bean
    Job Job3() {
        return this.jobBuilderFactory
                .get("job3")
                .incrementer(new RunIdIncrementer())
                .start(Step3()).build();
    }

    @Bean
    public Step Step1() {
        return stepBuilderFactory.get("Step1")
                .<String, String>chunk(1)
                .reader(new Reader())
                .processor(new Processor())
                .writer(new Writer())
                .build();
    }

    @Bean
    public Step Step2() {
        return stepBuilderFactory.get("Step2")
                .<String, String>chunk(1)
                .reader(new Reader2())
                .processor(new Processor())
                .writer(new Writer())
                .build();
    }


    @Bean
    Step Step3() {
        return this.stepBuilderFactory
                .get("Step3")
                .tasklet(new FileDeletingTasklet()).build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionListener();
    }
}
