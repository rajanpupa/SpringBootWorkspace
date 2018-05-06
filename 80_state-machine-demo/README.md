# 80 Spring_state_machine_demo

- Dependency `compile('org.springframework.statemachine:spring-statemachine-starter')`
- Configure SpringStateMachine

```
  @EnableStateMachine
  public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {


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
```

- Use the state machine

```
	@Autowired
	StateMachine<States, Events> stateMachine;
	
		stateMachine.start();
		stateMachine.sendEvent(event);
		return stateMachine.getState().getId();
```

## How to use
- Start the project (will run in port 8080)
- Initially the state will be State1.
- Send ?event=EVENT1
- The state will transition to State2
- so on

### Next
- How to send a state and event and get the next state?
- How to do a json based configuration

### References

- [quick start](https://projects.spring.io/spring-statemachine/#quick-start)

