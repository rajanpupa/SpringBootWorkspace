# Spring Boot | Env specific profiles

* Spring profiles helps group Components and configuration
* Enable or Disable per instance/Environment
* @Profile
* Enable with `spring.profiles.active`

#### Example1
* `@Profile("batch")` in GreetingsBatchBean
* Run the application, you will not see any loggings from the batch
* Run with command line argument `--spring.profiles.active=batch`. The profile is activated and you will see logs from the batch.


* Spring by default searches for application-<profile-name>.properties file in the resources/config folder, for the profile specific configuration.
* Profiles can be activated by adding the active profiled in the main application.properties file.
```
spring.profiles.active=batch
```

Link
https://youtu.be/0zjQX7WwjrI?list=PLGDwUiT1wr6-Fn3N2oqJpTdhGjFHnIIKY


	