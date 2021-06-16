package com.meylium.batch.controller;

import com.meylium.batch.service.JobService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobInvokerController {

    private final JobService jobService;

    public JobInvokerController(JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping("/start")
    public String start() {
        try {
            jobService.startUpperCaseJob();
            return "Job Started !";
        } catch (Exception e) {
            return "Exeption during job starting !";
        }
    }
}

