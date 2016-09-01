package com.boot.app.web.api;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.boot.app.AbstractControllerTest;
import com.boot.app.service.GreetingService;

import junit.framework.Assert;

@Transactional
public class GreetingControllerTest extends AbstractControllerTest{

	@Autowired
	private GreetingService greetingService;
	
	@Before
	public void setup(){
		super.setUp();
		greetingService.eviceCache();
	}
	
	@Test
	public void testGetGreetings() throws Exception{
		String uri = "/api/greetings";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();
		
		Assert.assertEquals("failure - status should be 200",200, status);
		Assert.assertTrue("failure - expected HTTP response body to have a value",
				content.trim().length()>0);;
	}
	
}
