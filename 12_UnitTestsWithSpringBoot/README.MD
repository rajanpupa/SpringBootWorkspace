# Spring Boot | Unit Tests

* Add the dependency for the test
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
```

* Create packages and the test classes in the `src/test/java` folder

* This is the base class for test
```
	@RunWith(SpringJUnit4ClassRunner.class)
	@SpringApplicationConfiguration(classes=Application.class)
	public abstract class AbstractTest {
```
* The `@RunWith` annotation tells spring which class to run the test with.

* The `@Transactional` annotation in the class, rolls back any destructive database operations in the test methods.

* Run the test with the following mvn commands.
``` 
mvn clean package
mvn test
```

* can skip the test during mvn build using the `-DskipTests` param



Link
https://youtu.be/WKD9E8KsQME?list=PLGDwUiT1wr6-Fn3N2oqJpTdhGjFHnIIKY


	