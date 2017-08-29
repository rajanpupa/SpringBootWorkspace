package com.example.mongo.configuration;

public interface ProfileBasedConfiguration {
	void connectToCacheServer();
	String getEnvironmentVariable();
	String getEnv(final String name);
}
