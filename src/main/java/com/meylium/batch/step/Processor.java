package com.meylium.batch.step;

import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String, String> {
    @Override
    public String process(String data) throws Exception {

        return data.toLowerCase();
    }
}
