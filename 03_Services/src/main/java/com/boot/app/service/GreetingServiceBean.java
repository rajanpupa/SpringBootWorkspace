package com.boot.app.service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.boot.app.model.Greeting;

@Service
public class GreetingServiceBean implements GreetingService{
	
	private static BigInteger nextId;
	private static Map<BigInteger, Greeting> greetingMap;

	static {
		Greeting g1 = new Greeting();
		g1.setText("Hello World!");
		save(g1);

		Greeting g2 = new Greeting();
		g2.setText("Hola mundoo");
		save(g2);
	}

	private static Greeting save(Greeting greeting) {
		if (greetingMap == null) {
			greetingMap = new HashMap<BigInteger, Greeting>();
			nextId = BigInteger.ONE;
		}

		// If Update
		if (greeting.getId() != null) {
			Greeting oldGreeting = greetingMap.get(greeting.getId());
			if (oldGreeting == null) {
				return null;
			}
			greetingMap.remove(greeting.getId());
			greetingMap.put(greeting.getId(), greeting);
			return greeting;
		}
		// If create
		greeting.setId(nextId);
		nextId = nextId.add(BigInteger.ONE);
		greetingMap.put(greeting.getId(), greeting);
		return greeting;
	}

	private static boolean remove(BigInteger id) {
		Greeting g = greetingMap.remove(id);
		if (g == null) {
			return false;
		}
		return true;
	}

	@Override
	public Collection<Greeting> fineAll() {
		 Collection<Greeting> greetings = greetingMap.values();
		 return greetings;
	}

	@Override
	public Greeting findOne(BigInteger id) {
		return greetingMap.get(id);
	}

	@Override
	public Greeting create(Greeting greeting) {
		return save(greeting);
	}

	@Override
	public Greeting update(Greeting greeting) {
		return save(greeting);
	}

	@Override
	public Boolean delete(BigInteger id) {
		return delete(id);
	}

}
