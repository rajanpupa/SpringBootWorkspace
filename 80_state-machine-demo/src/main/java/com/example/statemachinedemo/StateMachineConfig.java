package com.example.statemachinedemo;

import java.awt.Event;
import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
	
//	@Bean
//	public StateMachine<States, Events> stateMachine1()throws Exception{
//		Builder<States, Events> builder = StateMachineBuilder.builder();
//		
//		builder.configureStates()
//        .withStates()
//            .initial(States.State1)
//            .states(EnumSet.allOf(States.class));
//		
//		builder.configureTransitions()
//			.withExternal()
//			.source(States.State1).target(States.State2)
//			.event(Events.EVENT1)
//			.and()
//		.withExternal()
//			.source(States.State2).target(States.State3)
//			.event(Events.EVENT2)
//			.and()
//		.withExternal()
//			.source(States.State3).target(States.State1)
//			.event(Events.EVENT3)
//		;
//		
//		return builder.build();
//	}
	
	@Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
            .withStates()
                .initial(States.State1)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
            .withExternal()
                .source(States.State1).target(States.State2)
                .event(Events.EVENT1)
                .and()
            .withExternal()
                .source(States.State2).target(States.State3)
                .event(Events.EVENT2)
                .and()
            .withExternal()
            	.source(States.State3).target(States.State1)
            	.event(Events.EVENT3)
            ;
    }
    
    @WithStateMachine
    static class MyBean{
    	@OnTransition(target="State1")
    	void toState1() {
    		System.out.println(">> going to state1");
    	}
    	
    	@OnTransition(target="State2")
    	void toState2() {
    		System.out.println(">> going to state2");
    	}
    }
}
