package com.lucas.xim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XimApplication {
    private static final Logger logger = LoggerFactory.getLogger(XimApplication.class);

    public static void main(String[] args) {
        logger.debug("Xim server starting...");
        SpringApplication.run(XimApplication.class, args);
        logger.debug("Xim server started.");
    }


}
