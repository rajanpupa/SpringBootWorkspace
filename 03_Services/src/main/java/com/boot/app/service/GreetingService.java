package com.boot.app.service;

import java.math.BigInteger;
import java.util.Collection;

import com.boot.app.model.Greeting;

public interface GreetingService {

	Collection<Greeting> fineAll();
	
	Greeting findOne(BigInteger id);
	
	Greeting create(Greeting greeting);
	
	Greeting update(Greeting greeting);
	
	Boolean delete(BigInteger id);
}
