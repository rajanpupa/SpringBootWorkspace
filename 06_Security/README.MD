# Spring Boot | Security Example

Note: this project used in-memory hsqldb. In every run, it creates the schema, and populates the data with the sql files provided.


* Some of the Rest-controllers could be accessed publicly while some of them require authentication.
* Spring security being used to implement authentication and authorization.
* anyone can access `/home`, but to access `/hello` you need to be authorized

* Added the class `WebSecurityConfig.java` to configure security for corresponding paths.
* `http.csrf().disable()` is used to disable csrf checks. Since this application is not a web based application, this won't be a problem.


References:
https://spring.io/guides/gs/securing-web
https://stackoverflow.com/questions/38108357/how-to-enable-post-put-and-delete-methods-in-spring-security
http://www.codesandnotes.be/2015/02/05/spring-securitys-csrf-protection-for-rest-services-the-client-side-and-the-server-side/
http://docs.spring.io/spring-security/site/docs/3.2.5.RELEASE/reference/htmlsingle/#csrf-attacks
