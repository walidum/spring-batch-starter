package com.meylium.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.File;

public class FileDeletingTasklet implements Tasklet, InitializingBean {


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        String currentUsersHomeDir = System.getProperty("user.home");
        String otherFolder = currentUsersHomeDir + File.separator + "spring-batch";

        File dir = new File(otherFolder);
        Assert.state(dir.isDirectory());

        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            boolean deleted = files[i].delete();
            if (!deleted) {
                throw new UnexpectedJobExecutionException("Could not delete file " + files[i].getPath());
            } else {
                System.out.println(files[i].getPath() + " got deleted");
            }
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String currentUsersHomeDir = System.getProperty("user.home");
        String otherFolder = currentUsersHomeDir + File.separator + "spring-batch";
        File dir = new File(otherFolder);
        Assert.notNull(dir, "directory must be set");
    }

}
