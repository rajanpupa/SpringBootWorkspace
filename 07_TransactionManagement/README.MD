# Spring Boot | Transaction Management

* Add `@EnableTransactionManagement` annotation to the main method of Application class
* This informs spring to enable Transaction Management support within the application, and search the codebase for transaction boundaries

* `@Transactional` annotation could be added to the class level as well as the method level. GreetingServiceBean.java
* `propagation=Propagation.SUPPORTS` indicate that service method supports existing transaction but will not create new transaction.
* `readOnly=true` indicate the methods do not create or modify data
* readOnly will not help for create update or delete methods.
* Class level attributes of Transactional can be overwritten in the method level by using the same annotations.
* The `@Transactional` annotation should always be applied to the implementation and not the interface.

* To simulate a failure scenario, throw an exception from the create method, if the id is ==4. Any exception will trigger a rollback.

* Spring uses Proxy design pattern to achieve Transaction.

* @ResponseBody(required=false) in the delete method, eliminates the need to send body in the delete end-point



Link
https://youtu.be/hBO44wKy2zQ?list=PLGDwUiT1wr6-Fn3N2oqJpTdhGjFHnIIKY


	