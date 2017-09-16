package com.example.batchdemo;


import java.io.File;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableBatchProcessing
public class BatchDemoApplication {
	
	public static void main(String[] args) {
		
		System.setProperty("input1", "file:///" +  new File("in.csv").getAbsolutePath());
		
		System.setProperty("input2", "file:///" +  new File("in2.csv").getAbsolutePath());
		
		ConfigurableApplicationContext cac = SpringApplication.run(BatchDemoApplication.class, args);
		
//		for(String b : cac.getBeanDefinitionNames() ){
//			System.out.println(b);
//		}
	}
	
}
