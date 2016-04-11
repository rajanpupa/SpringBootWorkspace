package com.boot.app.controller;

import java.math.BigInteger;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.app.model.Greeting;
import com.boot.app.service.GreetingService;

@RestController
public class GreetingsController {
	
	@Autowired
	GreetingService greetingService;
	

	@RequestMapping(value = "/api/greetings", 
			method = RequestMethod.GET, 
			produces = {MediaType.APPLICATION_JSON_VALUE }
	)
	public ResponseEntity<Collection<Greeting>> getGreetings() {
		Collection<Greeting> greetings = greetingService.fineAll();
		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/greetings/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") BigInteger id) {
		Greeting greeting = greetingService.findOne(id);
		if (greeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/greetings", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
		Greeting savedGreeting = greetingService.create(greeting);
		return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/greetings/{id}", 
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {
		Greeting updatedGreeting = greetingService.update(greeting);
		if (updatedGreeting == null) {
			new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/greetings/{id}", 
			method = RequestMethod.DELETE, 
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") BigInteger id, @RequestBody Greeting greeting) {
		System.out.println("The delete id is :" + id);
		boolean flag = greetingService.delete(id);
		System.out.println("flag is " + flag);
		if (!flag) {
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
	}
}
