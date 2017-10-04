package com.boot.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
/**
 * Spring Boot main Application
 */
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{

    public static void main(String[] args) {
    	// this line is all you need for the basic Spring boot project
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("This runs after spring context is initialized.");
		
	}

}
