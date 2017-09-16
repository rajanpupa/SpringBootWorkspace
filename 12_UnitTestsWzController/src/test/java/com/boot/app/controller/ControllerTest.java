package com.boot.app.controller;

import javax.transaction.Transactional;




import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.boot.app.AbstractControllerTest;
import com.boot.app.service.GreetingService;

@Transactional
public class ControllerTest extends AbstractControllerTest {

	@Autowired
	private GreetingService greetingService;

	@Before
	public void setUp() {
		super.setUp();
		greetingService.eviceCache();
	}

	@Test
	public void testGetGreetings() throws Exception {
		String uri = "/api/greetings";

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri).accept(
						MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		Assert.assertEquals("failure - expected HTTP status 200", 200, status);
		Assert.assertTrue(
				"failure - expected HTTP response ody to have a value", content
						.trim().length() > 0);
	}
}
