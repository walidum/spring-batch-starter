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

    @RequestMapping("/start1")
    public String start1() {
        try {
            jobService.startJob1();
            return "Job 1 Started !";
        } catch (Exception e) {
            return "Exception during job 1 starting !";
        }
    }

    @RequestMapping("/start2")
    public String start2() {
        try {
            jobService.startJob2();
            return "Job 2 Started !";
        } catch (Exception e) {
            return "Exception during job 2  starting !";
        }
    }

    @RequestMapping("/start3")
    public String start3() {
        try {
            jobService.startJob3();
            return "Job 3 Started !";
        } catch (Exception e) {
            return "Exception during job 3  starting !";
        }
    }
}

