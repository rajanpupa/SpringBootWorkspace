package com.example.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.mongo.model.Customer;
import com.example.mongo.repository.CustomerRepository;

@SpringBootApplication
public class MongoApplication implements CommandLineRunner{
	
	@Autowired
	private CustomerRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repo.deleteAll();
		
		// save a couple of customers
		repo.save(new Customer("Alice", "Smith"));
		repo.save(new Customer("Bob", "Smith"));
		
		// fetch all customers
		System.out.println("Customers found with findAll()");
		System.out.println("------------------------------");
		for(Customer customer: repo.findAll()){
			System.out.println(customer);
		}
		System.out.println();
		
		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("---------------------------------------------");
		System.out.println(repo.findByFirstName("Alice"));
		
		System.out.println("Customers found with findByLastName('Smith'): ");
		System.out.println("----------------------------------------------");
		
		for(Customer customer: repo.findByLastName("Smith")){
			System.out.println(customer);
		}
	} 
}
