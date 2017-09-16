package com.example.batchdemo.controller;

import java.util.List;





import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {
	
	final String FIELD_END = ", ";
	final String LINE_END = "\n";
	
	@Autowired
	JobExplorer jobExplorer;
	
	@Autowired
	JobOperator jobOperator;
	
	@Autowired
	@Qualifier("job1")
	Job job1;
	
	@Autowired
	@Qualifier("job2")
	Job job2;

	/**
	 * lists the jobs that ran in batch
	 * 
	 * @return
	 * @throws NoSuchJobException
	 */
	@GetMapping("/list1")
	public ResponseEntity<String> listJobs() throws NoSuchJobException{
		List<JobInstance> jobInstances;
		
		List<String> jobNames = jobExplorer.getJobNames();
		StringBuilder ans = new StringBuilder();
		
		for( String jobName : jobNames ){
			jobInstances = jobExplorer.findJobInstancesByJobName( jobName, 0, jobExplorer.getJobInstanceCount( job1.getName() ) );
			for(JobInstance ji : jobInstances){
				for(JobExecution je : jobExplorer.getJobExecutions(ji)){
					ans.append("Job Name: " ).append(je.getJobInstance().getJobName()).append(FIELD_END)
					.append(" Job Id: ").append(je.getId()).append(FIELD_END)
					.append(" Start time: " ).append(je.getStartTime()).append(FIELD_END)
					.append(" End time: " ).append(je.getEndTime()).append(FIELD_END)
					.append(" Status : " ).append(je.getStatus()).append("\n");
				}
			}
		}
		
		return new ResponseEntity<String>(ans.toString(), HttpStatus.OK);
	}
	
	/**
	 * returns details about a particular jobid
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/job/{id}")
	public ResponseEntity<String> jobStatus(@PathVariable("id") String id){
		JobExecution je = jobExplorer.getJobExecution(Long.valueOf(id));
		
		if(je != null){
			return new ResponseEntity<String>(je.getExitStatus().getExitCode(), HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("Job not found", HttpStatus.OK);
		}
	}
	
	/**
	 * Restarts the jobid if present in batch 
	 * @throws JobParametersInvalidException 
	 * @throws JobRestartException 
	 * @throws NoSuchJobException 
	 * @throws NoSuchJobExecutionException 
	 * @throws JobInstanceAlreadyCompleteException 
	 * @throws NumberFormatException 
	 */
	@GetMapping("/restart/{id}")
	public ResponseEntity<String> restart(@PathVariable("id")String id) throws Exception{
//		jobExplorer.findRunningJobExecutions(arg0)
		JobExecution je = jobExplorer.getJobExecution(Long.valueOf(id));
		je.setStatus(BatchStatus.FAILED);
		long nid = jobOperator.restart(je.getId());
		
		return new ResponseEntity<String>("Job Launched. " + nid
				, HttpStatus.OK);
	}
}
