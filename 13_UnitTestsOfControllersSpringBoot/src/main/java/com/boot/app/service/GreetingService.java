package com.boot.app.service;

import java.util.Collection;

import com.boot.app.model.Greeting;

public interface GreetingService {

	Collection<Greeting> fineAll();
	
	Greeting findOne(Long id);
	
	Greeting create(Greeting greeting);
	
	Greeting update(Greeting greeting);
	
	void delete(Long id);
	
	void eviceCache();
}
