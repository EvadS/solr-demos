package com.se.sample.beans;

import org.springframework.beans.factory.annotation.Value;

public class MyBeans {
    @Value("${value.from.file}")
    private String valueFromFile;

    public String getValueFromFile() {
        return valueFromFile;
    }
}
