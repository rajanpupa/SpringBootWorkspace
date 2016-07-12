package com.boot.app.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.boot.app.model.Greeting;
import com.boot.app.repository.GreetingsRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GreetingServiceBean implements GreetingService {

	@Autowired
	private GreetingsRepository greetingsRepository;

	@Override
	public Collection<Greeting> fineAll() {
		Collection<Greeting> greetings = greetingsRepository.findAll();// .values();
		return greetings;
	}

	@Override
	public Greeting findOne(Long id) {
		return greetingsRepository.findOne(id);// .get(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Greeting create(Greeting greeting) {
		if (greeting.getId() != null) {
			// cannot create Greeting with specified ID value
			return null;
		}
		
		Greeting savedGreeting = greetingsRepository.save(greeting);// (greeting);
		
		if(savedGreeting.getId() == 4){
			throw new RuntimeException();
		}
		
		return savedGreeting;
	}

	@Override
	public Greeting update(Greeting greeting) {
		Greeting greetingPersisted = findOne(greeting.getId());

		if (greetingPersisted == null) {
			// Cannot update greeting that hasn't been persisted
			return null;

		}
		Greeting updatedGreeting = greetingsRepository.save(greeting);
		return updatedGreeting;
	}

	@Override
	public void delete(Long id) {
		greetingsRepository.delete(id);
	}

}
