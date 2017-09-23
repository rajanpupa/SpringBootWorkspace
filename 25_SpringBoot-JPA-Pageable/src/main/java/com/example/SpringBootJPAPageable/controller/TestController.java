package com.example.SpringBootJPAPageable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootJPAPageable.model.TestModel;
import com.example.SpringBootJPAPageable.repository.TestRepository;

@RestController
public class TestController {
	
	@Autowired
	TestRepository tr;

	@GetMapping
	public Page<TestModel> test(
			@RequestParam(value="page", required=false, defaultValue="1")Integer page,
			@RequestParam(value="size", required=false, defaultValue="5")Integer size
			){
		Pageable pg = new PageRequest(page, size);
		Page<TestModel> pm = tr.findAll(pg);
		return pm;
	}
}
