package com.boot.app.configurations;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages="com.boot.app.model",
    entityManagerFactoryRef = "mysqlEntityManager")
public class Configurations {
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource.test")
	public DataSource mysqlDatasource(){
		return DataSourceBuilder
				.create()
				.build();
	}

}
