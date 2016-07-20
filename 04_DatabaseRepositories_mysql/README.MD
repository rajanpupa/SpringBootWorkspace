# Spring Boot | Hsqldb connection example

Note: this project used in-memory hsqldb. In every run, it creates the schema, and populates the data with the sql files provided.

* Add the Jpa and hsqldb dependencies in the pom
* Create a Repository, extend with JPARepository
* Create resources/data/hsqldb folder and add schema.sql, data.sql
* Create config/application.properties which contains config information for jpa

*** If you dont want to re-create the database on every Restart***

* Remove the spring datasource and jpa entries from the `application.properties`
* Comment the configurations
```
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.generate-ddl=false

# Initialization
#spring.datasource.schema=classpath:/data/mysql/schema.sql
#spring.datasource.data=classpath:/data/mysql/data.sql
```


Link
https://youtu.be/4bPT-0f-am4


	