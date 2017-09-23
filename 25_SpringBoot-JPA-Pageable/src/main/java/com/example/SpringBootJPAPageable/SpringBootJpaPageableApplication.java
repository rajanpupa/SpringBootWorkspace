package com.example.SpringBootJPAPageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.SpringBootJPAPageable.model.TestModel;
import com.example.SpringBootJPAPageable.repository.TestRepository;

@SpringBootApplication
public class SpringBootJpaPageableApplication implements CommandLineRunner{

	@Autowired
	TestRepository tr;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaPageableApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		for(int i=0; i<15; i++){
			tr.save(new TestModel(i, "Hello "+i));
		}
	}
}
