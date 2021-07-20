# Spring Boot REST Controller JUnit Test Example

##Technology stack

Spring Boot 2.5.0  
Java version 11  
JUnit 5  

##Few of the points to notice here are:

 * **@WebMvcTest:** This annotation initializes web MVC related configurations required to write the JUnit test case for controller classes.
 * **MockMvc:** This class provides the required methods to test the Spring MVC layer. with perform() method, we can test different HTTP endpoints(GET, POST, PUT, DELETE, etc)
 * **@MockBean:** This annotation creates mocked beans in the spring application context.
 * **@Test:** Indicated that the method is a test case.
 * **Mockito:** This class of Mockito framework creates a mock of an object. We have mocked the return values of the service layer in our example.
 * **jsonpath:** Spring boot provides built-in supports JsonPath that is helpful to verify the JSON response.
 * **MvcResult:** MockMvc returns a result object on calling andReturn(), that contains the response details of a particular MVC operation.  
 
### More reference Documentation
#### Guides
The following guides illustrate how to use some features:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/projects/spring-restdocs)  