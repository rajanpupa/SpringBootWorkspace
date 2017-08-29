package com.example.mongo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class DevelopmentConfiguration implements ProfileBasedConfiguration{
	
	@Autowired
	private Environment env;

	@Override
	public void connectToCacheServer() {
		
	}

	@Override
	public String getEnvironmentVariable() {
		return "https://rjn.abc.com";
	}

	@Override
	public String getEnv(String name) {
		return env.getProperty(name);
	}

	
}
