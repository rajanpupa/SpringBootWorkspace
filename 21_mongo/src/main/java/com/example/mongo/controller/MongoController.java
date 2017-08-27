package com.example.mongo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongo.model.Customer;
import com.example.mongo.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class MongoController {

	@Autowired
	private CustomerRepository repository;
	
	@GetMapping
	public List<Customer> getCustomers(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id")String id){
		Optional<Customer> c = repository.findById(id);
		return ResponseEntity.status(c.isPresent()?HttpStatus.OK:HttpStatus.NOT_FOUND)
				.body(c.isPresent()?c.get():null);
	}
	
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer cust){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cust));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id")String id, @RequestBody Customer cust){
		cust.setId(id);
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(cust));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value=HttpStatus.ACCEPTED)
	public void removeCustomer(@PathVariable("id")String id,@RequestBody Customer cust){
		repository.deleteById(id);
	}
	
}
