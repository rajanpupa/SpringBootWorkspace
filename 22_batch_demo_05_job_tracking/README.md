# Batch Demo | separate databases | job tracking

* this application uses separate databases for batch specific data and actual business data

The `spring-batch` database will be used as batch database, and
`spring-batch-data` will be used as business database.

## Problems being faced

* Problem running the jobs asynchronously while returning the jobid immediately
* How to track the jobid? (failed, passed, running)
* How to limit number of jobs instances running in the application.
* Restart a particular jobId.

### Async launch the job

```
	@Bean
	public JobLauncher jobLauncher(){
		final SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository);
		
		final SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
		
		jobLauncher.setTaskExecutor(simpleAsyncTaskExecutor);
		
		return jobLauncher;
	}
```

### References

https://aboullaite.me/spring-batch-tutorial-with-spring-boot/
https://youtu.be/Hqhgci5OIvg
