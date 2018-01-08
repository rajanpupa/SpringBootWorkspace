# Data Mover

This project helps move data from one database to another very fast.

Test, moved 8K records in 5-10 seconds.

Run the project

```
mvn spring-boot:run
```

There is an endpoint `/move` where you can provide a table name and where clause
and the application moves data from the source database to the target database

Table names should be same in both the database.

### To make it work,

* Include the appropriate `JDBCDriver` dependency in the pom file.

* Edit the `source` and `target` database properties

### JMeter
`Need to have jmeter.properties file in src\test\jmeter folder`

`.\gradlew jmeterRun`



	