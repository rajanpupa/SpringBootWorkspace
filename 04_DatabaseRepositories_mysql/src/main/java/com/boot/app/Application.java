package com.boot.app;

/**
 * Spring Boot main Application
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	// this line is all you need for the basic Spring boot project
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

}
