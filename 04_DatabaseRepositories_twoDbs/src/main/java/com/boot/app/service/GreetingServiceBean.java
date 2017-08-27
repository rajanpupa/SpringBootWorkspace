package com.boot.app.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.app.foo.model.Greeting;
import com.boot.app.foo.repository.GreetingsRepository;

@Service
public class GreetingServiceBean implements GreetingService{
	
	@Autowired
	private com.boot.app.foo.repository.GreetingsRepository fooGreetingsRepository;
	
	@Autowired
	private com.boot.app.bar.repository.GreetingsRepository barGreetingsRepository;
	
	@Override
	public Collection<Greeting> fineAll() {
		 Collection<Greeting> greetings = fooGreetingsRepository.findAll();//.values();
		 return greetings;
	}

	@Override
	public Greeting findOne(Long id) {
		return fooGreetingsRepository.findOne(id);//.get(id);
	}

	@Override
	public Greeting create(Greeting greeting) {
		if(greeting.getId() != null){
			//cannot create Greeting with specified ID value
			return null;
		}
		Greeting savedGreeting = fooGreetingsRepository.save(greeting);//(greeting);
		
		com.boot.app.bar.model.Greeting barGreeting = new com.boot.app.bar.model.Greeting();
		barGreeting.setText(greeting.getText());
		barGreetingsRepository.save(barGreeting);
		
		return savedGreeting;
	}

	@Override
	public Greeting update(Greeting greeting) {
		Greeting greetingPersisted = findOne(greeting.getId());
		
		if(greetingPersisted== null){
			// Cannot update greeting that hasn't been persisted
			return null;
			
		}
		Greeting updatedGreeting = fooGreetingsRepository.save(greeting);
		
		com.boot.app.bar.model.Greeting barGreeting = new com.boot.app.bar.model.Greeting();
		barGreeting.setText(greeting.getText());
		barGreetingsRepository.save(barGreeting);
		
		return updatedGreeting;
	}

	@Override
	public void delete(Long id) {
		fooGreetingsRepository.delete(id);
	}

}
