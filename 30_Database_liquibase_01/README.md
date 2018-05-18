# 30 Database Liquibase 01

In this project, we create various tasks in gradle and configure liquibase.

When the gradle task is run, liquibase applies the changes to the table configured.

```
gradle task dev update
```

It creates two extra tables, which must be the metadata tables. 

* databasechangelog
* databasechangeloglock

The main table created is `test1`, with columns id and name, and the data is loaded 
as defined in the scripts in the `scripts` folder in resources.

## steps

* add the appropriate dependencies in the buildscript section of build.gradle

```
		classpath 'org.liquibase:liquibase-core:3.4.1'
    	classpath "org.liquibase:liquibase-gradle-plugin:1.1.1"
		classpath 'mysql:mysql-connector-java:5.1.13'
```

* declare the `liquibase` plugin and `Changelog.xml` file

```
apply plugin: 'liquibase'
def changeLog = "$projectDir/src/main/resources/Changelog.xml"
```

* Changelog.xml file

```
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
    xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:ext="http://www.liquibase.org/xml/ns/dbchangelog-ext"
    xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.1.xsd
    http://www.liquibase.org/xml/ns/dbchangelog-ext http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd">
    
    <include file="scripts/001.SAMPLE.sql" relativeToChangelogFile="true"/>
    <include file="scripts/002.SAMPLE.sql" relativeToChangelogFile="true"/>

</databaseChangeLog>
```

* Put the sql files in the defined location

* run gradle task `gradle task dev update`

## References

[DZone](https://dzone.com/articles/managing-your-database-with-liquibase-and-gradle)
https://www.jeejava.com/spring-boot-liquibase-gradle-example/
[Medium detail tutorial](https://medium.com/@harittweets/evolving-your-database-using-spring-boot-and-liquibase-844fcd7931da)