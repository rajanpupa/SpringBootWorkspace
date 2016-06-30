package com.boot.app;

/**
 * Spring Boot main Application
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Throwable{
    	// this line is all you need for the basic Spring boot project
        SpringApplication.run(Application.class, args);
    }

}
