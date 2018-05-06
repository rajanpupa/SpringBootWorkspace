package com.example.statemachinedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StatesDemoController {
	
	@Autowired
	StateMachine<States, Events> stateMachine;
	
	@GetMapping("/transit")
	public States transit(
			@RequestParam(value="state", required=false)States initialState,
			@RequestParam("event")Events event
			) {
		stateMachine.start();
		stateMachine.sendEvent(event);
		return stateMachine.getState().getId();
	}

}
