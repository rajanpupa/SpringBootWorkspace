# Spring Boot | Asynchronous Processes

`@EnableAsync`
* This tag enable spring to search for annotations related Async annotation.

* Create EmailService interface and implementation. create a sendEmail method in the greetingsController.

How to check

`localhost:8080/api/greetings/1/send?wait=true`
* this will return in more then 5000 ms, because its returning only when the async task is returning.

`localhost:8080/api/greetings/1/send`
* this endpoint will return much faster, because its triggering and then not waiting for the async task to complete. Its returning immediately.


### Points to Note

* Special care should be taken for logging, because its tricky to debug.
* Asynchronous task is mostly used to run offline, long running tasks, where the user do not have to wait in the UI, and can start doing some other things.

Link
https://youtu.be/TEMsEcdAsbY?list=PLGDwUiT1wr6-Fn3N2oqJpTdhGjFHnIIKY


	