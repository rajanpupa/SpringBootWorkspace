package com.example.aopdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspects {

	@Before("execution(* com.example.aopdemo.service.TestService.* ()) && args()")
    public void beforeSampleCreation() {
        System.out.println("@Before log from the aop: ");
    }
	
	@After("execution(* com.example.aopdemo.service.TestService.* ()) && args()")
    public void afterSampleCreation() {
        System.out.println("@After log from the aop: ");
    }
	
	@Around("execution(* com.example.aopdemo.service.TestService.* ()) && args()")
    public void aroundSampleCreation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
 
        System.out.println("@Around advice for the aop: start");
 
        proceedingJoinPoint.proceed();
        
        System.out.println("@Around advice for the aop: end");
 
    }
	
	
	
}
