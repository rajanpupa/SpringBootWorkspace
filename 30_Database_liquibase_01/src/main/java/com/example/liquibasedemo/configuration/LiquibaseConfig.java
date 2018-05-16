package com.example.liquibasedemo.configuration;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquibaseConfig {
	@Bean
	public SpringLiquibase liquibase() {
	    SpringLiquibase liquibase = new SpringLiquibase();
	    liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
	    liquibase.setDataSource(dataSource());
	    return liquibase;
	}
	

	@ConfigurationProperties(prefix="spring.datasource1")
	public DataSource dataSource(){
		return null;
	}

}
