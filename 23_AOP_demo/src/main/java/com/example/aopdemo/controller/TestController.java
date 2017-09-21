package com.example.aopdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aopdemo.service.TestService;

@RestController
public class TestController {

	@Autowired
	TestService tservice;
	
	@GetMapping
	public String aopTest(){
		tservice.test();
		
		return "Controller working. Look at the logs for the aop log";
	}
}
