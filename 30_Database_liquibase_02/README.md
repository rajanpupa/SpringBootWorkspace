# 30 Database Liquibase 01

* put `liquibase-core` dependency in gradle
* specify the datasource properties in `application.properties`
* specify the liquibase changelog master filepath

```
spring.liquibase.change-log=classpath:data/db.changelog-master.xml
spring.liquibase.enabled=true
```

* define all the changeSets
* start your springboot project, on startup, liquibase will create the tables for you. 


## References
(https://www.youtube.com/watch?v=7VeODrRkHXg)