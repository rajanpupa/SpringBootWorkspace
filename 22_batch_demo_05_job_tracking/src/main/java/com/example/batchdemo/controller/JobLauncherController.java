package com.example.batchdemo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("job1")
	Job job1;
	
	@Autowired
	@Qualifier("job2")
	Job job2;
	
	@RequestMapping("/launchjob")
	public String handle() throws Exception{
		Logger logger = LoggerFactory.getLogger(this.getClass());
		Long jobid = -1L;
		try{
			JobParameters jobParameters = new JobParametersBuilder()
					.addLong("time", System.currentTimeMillis())
					.toJobParameters();
			JobExecution je =jobLauncher.run(job1, jobParameters);
			jobid = je.getId();
			return "Done " + jobid;
			
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		
		return "Done " + jobid;
	}
	
	@RequestMapping("/launchjob2")
	public void handle2() throws Exception{
		jobLauncher.run(job2, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters() );
	}
}
