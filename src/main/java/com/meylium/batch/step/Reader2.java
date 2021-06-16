package com.meylium.batch.step;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class Reader2 implements ItemReader<String> {
    private String[] messages = {
            "walidum.dev 2",
            "Welcome to Spring Batch Example 2 ",
            "I use H2 Database for this example 2"
    };
    private int count = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (count < messages.length) {
            return messages[count++];
        } else {
            count = 0;
        }
        return null;
    }
}
