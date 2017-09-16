# Batch Demo | separate databases

* this application uses separate databases for batch specific data and actual business data

** First put `@Primary` in the `batchDataSource()` method of the DatasourceConfigs.
** Start the application `mvn spring-boot:run`
** This will  batch databases in the `spring-batch` database.

** Now stop the application, and move the `@Primary` annotation to `mainDataSource` method.
** Put this line in the `application.properties`

```
spring.batch.initializer.enabled=false
```

** this will tell spring not to initialize the spring-batch tables.

Now, if you run the application, the `spring-batch` database will be used as batch database, and
`spring-batch-data` will be used as business database.


# This project
This job launches batch job from a rest controller

```
localhost:8080/launchjob
localhost:8080/launchjob2
```

* the file has 10000 lines the batch job picks and inserts to database.
* Each record will wait for 5 seconds

# Problems being faced

* Problem running the jobs asynchronously while returning the jobid immediately
* How to track the jobid? (failed, passed, running)
* How to limit number of jobs instances running in the application.
* Restart a particular jobId.

### References

https://aboullaite.me/spring-batch-tutorial-with-spring-boot/
https://youtu.be/Hqhgci5OIvg