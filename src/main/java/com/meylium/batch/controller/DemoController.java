package com.meylium.batch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("hi")
    public String hi() {
        return "Hello word !";
    }
}

