package com.se.sample.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class Startup implements CommandLineRunner {
    Logger log = LoggerFactory.getLogger(Startup.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("Started ....");
    }
}
