package com.boot.app.controller;

import java.util.Collection;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.app.model.Greeting;
import com.boot.app.service.EmailService;
import com.boot.app.service.GreetingService;

@RestController
public class GreetingsController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	GreetingService greetingService;
	
	@Autowired
	private EmailService emailService;

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
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id) {
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
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Long id, 
			@RequestBody(required=false) Greeting greeting) {
		System.out.println("The delete id is :" + id);
		greetingService.delete(id);
		return new ResponseEntity<Greeting>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(
			value="/api/greetings/{id}/send",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> sendGreeting(@PathVariable("id")Long id,
			@RequestParam(
					value="wait",
					defaultValue="false")boolean waitForAsyncResult){
		
		logger.info("> sendGreeting");
		Greeting greeting = null;
		
		try{
			greeting = greetingService.findOne(id);
			if(greeting == null){
				logger.info("<sendGreeting");
				return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
			}
			
			if(waitForAsyncResult){// this block will wait until the async task is completed.
				Future<Boolean> asyncResponse = emailService.sendAsyncWithResult(greeting);
				boolean emailSent = asyncResponse.get();
				logger.info("- greeting email sent? {}", emailSent);
			}else{// this block will trigger the send method and return immediately
				emailService.sendAsync(greeting);
			}
		}catch(Exception e){
			logger.error("A problem occured sending the Greeting.", e);
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("< sendGreeting");
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}
}
