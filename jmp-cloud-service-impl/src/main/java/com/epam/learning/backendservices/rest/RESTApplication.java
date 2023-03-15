package com.epam.learning.backendservices.rest;

import com.epam.learning.backendservices.rest.config.SpringConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SpringConfig.class})
public class RESTApplication {

    public static void main(String[] args) {
        SpringApplication.run(RESTApplication.class, args);
    }

}
