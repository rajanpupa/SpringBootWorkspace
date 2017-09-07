# Batch Demo

* Generate project from start.spring.io
* Include dependencies 

```
mysql
jdbc
batch
```

* Use the annotation `@EnableBatchProcessing` to use spring batch

* Default configuration of spring-boot is to run job on application startup

* Job is made up of several `steps` or `stages`

```
Reader
Processor
Writer
```

* Create a `@Bean` named `Job`
* Add various steps in the `JobBuilderFactory` to create the job
* Job has various steps.
* Each step has Various reader, processor, writer
* Can define chunk() size in the step.

* ItemReader Bean has to be declared 
* Itemreader implementation, reads data from some resource, and return some object
* Processor can take some object as input, and can do some processing and return some object as output
* if Processor returned null for some input object, that object is simply ignored
* Writer takes some object as input, and writes it to some place (file, db, queue etc)


### References

https://aboullaite.me/spring-batch-tutorial-with-spring-boot/