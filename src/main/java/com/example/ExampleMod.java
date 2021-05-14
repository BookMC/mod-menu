package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleMod {
    private final Logger logger = LogManager.getLogger(this);

    public static ExampleMod INSTANCE = new ExampleMod();

    public void start() {
        logger.info("ExampleMod was loaded! :)");
    }

    public Logger getLogger() {
        return logger;
    }
}
