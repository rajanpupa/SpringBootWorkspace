package com.example.batchdemo;


import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.example.batchdemo.configs.BatchConfiguration;

@SpringBootApplication
@Import(BatchConfiguration.class)
@EnableAsync
public class BatchDemoApplication {
	
	public static void main(String[] args) {
		
		System.setProperty("input1", "file:///" +  new File("in.csv").getAbsolutePath());
		
		System.setProperty("input2", "file:///" +  new File("in2.csv").getAbsolutePath());
		
		ConfigurableApplicationContext cac = SpringApplication.run(BatchDemoApplication.class, args);
		
	}
	
}
