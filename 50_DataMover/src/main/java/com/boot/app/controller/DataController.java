package com.boot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boot.app.service.DataService;

@RestController
public class DataController {

	@Autowired
	DataService ds;
	
	@GetMapping
	public String index(){
		return "DataMover api working";
	}
	
	@GetMapping(value="/move")
	public Integer move(@RequestParam("table")String table, @RequestParam("where")String whereCondition){
		System.out.println("table: " + table);
		System.out.println("where: " + whereCondition);
		
		int count = ds.move(table, whereCondition);
		
		return count ;
	}
	
}
