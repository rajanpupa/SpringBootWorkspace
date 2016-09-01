# Spring Boot | Swagger

* Swagger is a plugin/dependency in spring Boot application
* Its basically provides a very good UI, so that developers could test their restful endpoints

```
		<dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.2.2</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.2.2</version>
            <scope>compile</scope>
        </dependency>
```

* Enable swagger by this annotation in the Application class
```
@EnableSwagger2
```

* Implement these methods in the application class

```
 	@Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("greetings")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/greeting.*"))
                .build();
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST Sample with Swagger")
                .description("Spring REST Sample with Swagger")
                .termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open")
                .contact("Niklas Heidloff")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
                .version("2.0")
                .build();
    }
```

* In the controller, use Swagger annotations to document the API. Must specify the inputs.

* Try the URL. you will get to the swagger page.
`http://localhost:8080/swagger-ui.html`

### Reference
http://springfox.github.io/springfox/docs/current/







	