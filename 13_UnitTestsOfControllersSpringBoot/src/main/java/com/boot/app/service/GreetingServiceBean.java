package com.boot.app.service;

import java.util.Collection;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
	@CachePut(value="greetings", key="#result.id")
	public Greeting create(Greeting greeting) {
		if (greeting.getId() != null) {
			// cannot create Greeting with specified ID value
			throw new EntityExistsException(
                    "The id attribute must be null to persist a new entity.");
		}
		
		Greeting savedGreeting = greetingsRepository.save(greeting);// (greeting);
		
		if(savedGreeting.getId() == 4){
			throw new RuntimeException();
		}
		
		return savedGreeting;
	}

	@Override
	@CachePut(value="greetings", key="#greeting.id")
	public Greeting update(Greeting greeting) {
		Greeting greetingPersisted = findOne(greeting.getId());

		if (greetingPersisted == null) {
			// Cannot update greeting that hasn't been persisted
			throw new NoResultException("Requested entity not found.");

		}
		Greeting updatedGreeting = greetingsRepository.save(greeting);
		return updatedGreeting;
	}

	@Override
	@CacheEvict(value="greetings", key="#id")
	public void delete(Long id) {
		greetingsRepository.delete(id);
	}
	
	@Override
	@CacheEvict(value="greetings", allEntries=true)
	public void eviceCache() {
		
	}

}
