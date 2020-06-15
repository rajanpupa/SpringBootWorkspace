package com.example.neo4jexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Import;


@SpringBootApplication
//@Import({ springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class})
public class SpringRetryDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRetryDemoApplication.class, args);
	}

}
