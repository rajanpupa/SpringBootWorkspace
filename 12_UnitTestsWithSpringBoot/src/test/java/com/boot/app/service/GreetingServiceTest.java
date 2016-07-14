package com.boot.app.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.boot.app.AbstractTest;
import com.boot.app.model.Greeting;

@Transactional
public class GreetingServiceTest extends AbstractTest {

	@Autowired
	private GreetingService service;
	
	@Before
	public void setup(){
		service.eviceCache();
	}
	
	@After
	public void tearDown(){
		// clean up after each test method
	}
	
	@Test
	public void testFindAll(){
		Collection<Greeting> list = service.fineAll();
		
		Assert.assertNotNull("failure - expected not null", list);
		Assert.assertEquals("failure - expected size",  2, list.size());
		
	}
}
