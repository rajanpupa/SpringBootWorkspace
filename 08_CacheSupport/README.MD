# Spring Boot | Local Cache Support

* Add `@EnableCaching` annotation to the Application Class. This informs spring to enable the cache mgmt support inside the application.
* and search the method with cache related annotations.

* Next, we need to define a Cache Manager bean, which spring uses, to manage cache. (done in Application.java)

* now, we can annotate a ServiceBean method with `@Cachable(value='greetings', key='#id')` annotation.
* the value attribute defines which cache, the values are going to get stored for that method.
* the key attribute defines, which value of the object are going to be used as a key on that cache.

* `@CachePut` annotations for the methods whose results needs to be added in cache
* `@CacheEvict` annotation for those methods whose results needs to be removed from cache

* The concurrentCacheMap is suitable for np- environment. For Production, use EA cache or guava is recommended.
* For distributed caching support, use distributed cache managing apis

* Now add guava dependency to the pom.

Link
https://youtu.be/g4h268Hx0AU?list=PLGDwUiT1wr6-Fn3N2oqJpTdhGjFHnIIKY


	