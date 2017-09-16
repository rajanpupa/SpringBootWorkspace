package com.example.batchdemo.configs;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class DatasourceConfigs {
	
	 @Value("classpath:schema.sql")
	 private Resource schemaScript;

	private final static Logger log = LoggerFactory.getLogger(DatasourceConfigs.class);
	
	@Autowired 
	private Environment env;
	
	@Primary
	@Bean(name="mainDataSource")
	public DataSource mainDataSource() throws SQLException{
		final String user = this.env.getProperty("db.main.username");
		final String pass = this.env.getProperty("db.main.password");
		final String url = this.env.getProperty("db.main.url");
		
		System.out.println("-- created mainDatasource");
		return this.getMysqlDataSource(url, user, pass);
	}
	
	@Bean(name="batchDataSource")
	public DataSource batchDataSource() throws SQLException{
		final String user = this.env.getProperty("db.batch.username");
		final String pass = this.env.getProperty("db.batch.password");
		final String url = this.env.getProperty("db.batch.url");
		
		DataSource ds = this.getMysqlDataSource(url, user, pass);
		DatabasePopulatorUtils.execute(databasePopulator(), ds);
		
		System.out.println("-- created batchDataSource");
		return ds;
	}

	private DataSource getMysqlDataSource(String url, String user, String pass) throws SQLException {
		final SimpleDriverDataSource  mysql = new SimpleDriverDataSource();
		mysql.setDriver(new com.mysql.jdbc.Driver());
		mysql.setUsername(user);
		mysql.setPassword(pass);
		mysql.setUrl(url);
		return mysql;
	}
	
	 private DatabasePopulator databasePopulator() {
	        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
	        populator.addScript(schemaScript);
	        return populator;
	    }
}
