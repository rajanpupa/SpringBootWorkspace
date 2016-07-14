package com.boot.app.service;

import java.util.Collection;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
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
	
	// runs before each test method
	@Before
	public void setup(){
		logger.info("< setup");
		// remove all the cache
		service.eviceCache();
	}
	
	// runs after each test method
	@After
	public void tearDown(){
		// clean up after each test method
		logger.info("< tearDown");
	}
	
	@Test
	public void testFindAll(){
		logger.info("< testFindAll");
		Collection<Greeting> list = service.fineAll();
		
		Assert.assertNotNull("failure - expected not null", list);
		Assert.assertEquals("failure - expected size",  2, list.size());
		
	}
	
	@Test
	public void testFindOne(){
		logger.info("< testFindOne");
		Long id = new Long(1);
		Greeting entity = service.findOne(id);
		
		Assert.assertNotNull("Failure - expected not null", entity);
		Assert.assertEquals("Failure - expected id attribute match",id,entity.getId());
	}
	
	@Test
	public void findOneNotFound(){
		logger.info("< findOneNotFound");
		Long id = Long.MAX_VALUE;
		
		Greeting entity = service.findOne(id);
		Assert.assertNull("Failure - expected null", entity);
	}
	
	@Test
	public void testCreate(){
		logger.info("< testCreate");
		Greeting entity = new Greeting();
		entity.setText("test");
		
		Greeting createdEntity = service.create(entity);
		
		Assert.assertNotNull("failure - expected not null", createdEntity);
		Assert.assertNotNull("failure - expected id attributenot null",
				createdEntity.getId());
		Assert.assertEquals("failure - expected text attribute match",
				"test",createdEntity.getText());
		
		Collection<Greeting> list = service.fineAll();
		
		Assert.assertEquals("failure - expected size", 3, list.size());
	}
	
	@Test
	public void testCreateWithId(){
		logger.info("<	testCreateWithId");
		Exception e = null;
		
		Greeting entity = new Greeting();
		entity.setId(Long.MAX_VALUE);
		entity.setText("test");
		
		try{
			// should throw exception 
			// if the entity has an id value
			service.create(entity);
		}catch(EntityExistsException eee){
			e=eee;
		}
		
		Assert.assertNotNull("failure - excepted exception", e);
		Assert.assertTrue("failure - expected EntityExistsException",
				e instanceof EntityExistsException);
	}
	
	@Test
	public void testUpdate(){
		logger.info("< testUpdate");
		Long id = new Long(1);
		Greeting entity = service.findOne(id);
		
		Assert.assertNotNull("failure - expected not null", entity);
		
		String updatedText = entity.getText() + " test";
		entity.setText(updatedText);
		Greeting updatedEntity = service.update(entity);
		
		Assert.assertNotNull("failure - updated entity not null", updatedEntity);
		Assert.assertEquals("failure - expected updated entity id attribute unchanged",
				id, updatedEntity.getId());
		Assert.assertEquals("failure - expected updated entity text attribute match",
				updatedText, updatedEntity.getText());
	}
	
	@Test
	public void testUpdateNotFound(){
		logger.info("< testUpdateNotFound");
		Exception e = null;
		
		Greeting entity = new Greeting();
		entity.setId(Long.MAX_VALUE);
		entity.setText("test");
		
		try{
			service.update(entity);
		}catch(NoResultException ee){
			e = ee;
		}
		
		Assert.assertNotNull("failure - expected exception", e);
		Assert.assertTrue("failure - expected NoResultException",
				e instanceof NoResultException);
	}
	
	@Test
	public void testDelete(){
		logger.info("< testDelete");
		Long id = new Long(1);
		
		Greeting entity = service.findOne(id);
		Assert.assertNotNull("failure - expected not null", entity);
		
		service.delete(id);
		
		Collection<Greeting> list = service.fineAll();
		
		Assert.assertEquals("failure - expected size", 1, list.size());
		
		Greeting deleteEntity = service.findOne(id);
		
		Assert.assertNull("failure - expected entity to be deleted",
				deleteEntity);
	}
	
}



