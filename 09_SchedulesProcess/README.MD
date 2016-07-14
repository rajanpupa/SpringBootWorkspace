# Spring Boot | Scheduled Processes

* Add `@EnableScheduling` annotation to the main Application class.
* After that we need to create a component with the `@Scheduled` method annotation with the method to be scheduled.

### See the GreetingBatchBean.java

```
@Scheduled(
			cron = "0,30 * * * * *")
```

* The corn job above, makes the method in the GreetingsBatchBean to run every 30 seconds.

* The second type of scheduled job is the FixedRate job.
```
@Scheduled(
			initialDelay=5000, 
			fixedRate=15000)
```
* This job will be fired every 15 seconds, regardless of whether the previous job has finished execution.
* The first job will be fired 5 seconds after the initialization of application context.
* Multiple instances of this job is possible at a time

* The third type of scheduled job is the FixedDelay job.
```
@Scheduled(
			initialDelay=5000,
			fixedDelay=15000)
```

* This job is fired exactly 15 seconds after the first job has finished execution.
* The first job will be fired 5 seconds after the initialization of application context.
* Only one job can be running at a time, because the next job will not fire until the first job has finished executing.


Link
https://youtu.be/TEMsEcdAsbY?list=PLGDwUiT1wr6-Fn3N2oqJpTdhGjFHnIIKY


	