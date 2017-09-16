package com.boot.app.batch;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.boot.app.model.Greeting;
import com.boot.app.service.GreetingService;

@Profile("batch")
@Component
public class GreetingBatchBean {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GreetingService greetingService;
	
	// this method will be scheduled every 30 seconds
	@Scheduled(
			cron = "${batch.greeting.cron}")
	public void cronJob(){
		logger.info("> cronJob");
		
		// Add scheduled logic here
		Collection<Greeting> greetings = greetingService.fineAll();
		
		logger.info("There are {} greetings in the data store.",
				greetings.size());
		
		logger.info("< cornJob");
	}
	
	/**
	 * This method will be invoked every 15 seconds, by spring scheduler.
	 * The first execution will occur, 5 seconds after the application startup(app context is initialized).
	 * A new process will start after the configured time has elapsed, even if the previous execution has not yet completed.
	 */
	@Scheduled(
			initialDelayString="${batch.greeting.initialdelay}", 
			fixedRateString="${batch.greeting.fixedrate}")
	public void fixedRateJobWithInitialDelay(){
		logger.info("> fixedRateJobWithInitialDelay");
		
		// Add scheduled logic here
		// Simulate job processing time
		
		long pause = 5000;
		long start = System.currentTimeMillis();
		do{
			if(start + pause < System.currentTimeMillis()){
				break;
			}
		}while(true);
		logger.info("Processing time was {} seconds.", pause / 1000);
		
		logger.info("< fixedRateJobWithInitialDelay");
	}
	
	
	/**
	 * In FixedDelay jobs, the next job will be fired exactly 15seconds after the previous job has ended.
	 * Only one job can be running at a time.
	 * The first execution is done 5seconds after the initialization of application context.
	 */
	@Scheduled(
			initialDelayString="${batch.greeting.initialdelay}",
			fixedDelayString="${batch.greeting.fixeddelay}")
	public void fixedDelayJobWithInitialDelay(){
		logger.info("> fixedRateJobWithInitialDelay");
		
		// Add scheduled logic here
		// Simulate job processing time
		
		long pause = 5000;
		long start = System.currentTimeMillis();
		do{
			if(start + pause < System.currentTimeMillis()){
				break;
			}
		}while(true);
		logger.info("Processing time was {} seconds.", pause / 1000);
		
		logger.info("< fixedRateJobWithInitialDelay");
	}

}
