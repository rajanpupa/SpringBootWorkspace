package com.boot.app.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configurable
@Scope(value="prototype")
public class DatasourceConfigurations {
	
	final String DRIVER = 	"spring.datasource.driver";
	final String URL = 		"spring.datasource.url";
	final String USERNAME = "spring.datasource.username";
	final String PASSWORD = "spring.datasource.password";

	@Autowired
	private Environment env;
	
	@Bean(name="SOURCE")
	public Connection getSourceConnection(){
		String src = "source.";
		String jdbcClassName = this.env.getProperty(src + DRIVER);
		String url = this.env.getProperty(src + URL);
		String user = this.env.getProperty(src + USERNAME);
		String password = this.env.getProperty(src + PASSWORD);
		
		Connection connection = null;
		try{
			Class.forName(jdbcClassName);
			connection = DriverManager.getConnection(url, user, password);
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return connection;
	}
	
	@Primary
	@Bean(name="TARGET")
	public Connection getTargetConnection(){
		String src = "target.";
		String jdbcClassName = this.env.getProperty(src + DRIVER);
		String url = this.env.getProperty(src + URL);
		String user = this.env.getProperty(src + USERNAME);
		String password = this.env.getProperty(src + PASSWORD);
		
		Connection connection = null;
		try{
			Class.forName(jdbcClassName);
			connection = DriverManager.getConnection(url, user, password);
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return connection;
	}
}
