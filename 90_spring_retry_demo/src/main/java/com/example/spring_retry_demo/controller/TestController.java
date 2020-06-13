package com.example.spring_retry_demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_retry_demo.exception.ProcessingException;
import com.example.spring_retry_demo.service.RetryService;

@RestController
public class TestController {
	
	@Autowired
	RetryService retryService;

	@GetMapping("/retry")
	public Object retryTest() {
		Map abc = new HashMap<String, String>();
		try {
			abc.put("tryValue", retryService.retry());
		}catch(ProcessingException pe) {
			System.out.println("caught exception");
		}
		
		return abc;
	}
}
