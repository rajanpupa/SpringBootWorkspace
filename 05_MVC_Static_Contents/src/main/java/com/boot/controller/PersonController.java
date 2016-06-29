package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.model.Person;

@Controller
public class PersonController {

	@RequestMapping(value="/person", method=RequestMethod.GET)
	public String person(Model model){
		Person p = new Person();
		p.setFirstName("Rajan");
		p.setLastName("upadhyay");
		p.setAge(25);
		model.addAttribute("person", p);
		
		System.out.println("In the /person controller");
		return "personForm";
	}
	
	@RequestMapping(value="/person", method=RequestMethod.POST)
	public String createPerson(@ModelAttribute Person person, Model model){
		model.addAttribute("person", person);
		
		System.out.println("In the /person controller");
		return "result";
	}
	
	@RequestMapping(value="/personAjax", method=RequestMethod.POST)
	public @ResponseBody Person createPersonAjax(@RequestBody Person person){
		
		System.out.println("In the /person controller");
		return person;
	}
}
