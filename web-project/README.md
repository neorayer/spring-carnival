Web Project
===========

## Swagger

### Reference
https://springfox.github.io/springfox/docs/current

### Endpoints
http://localhost:8080/swagger-ui
http://localhost:8080/api-docs/v2

### build.gradle

```
    compile "io.springfox:springfox-swagger2:2.7.0"
```

### annotation
```
@SpringBootApplication
@EnableSwagger2
public class Application{
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### configuration of ui
```
    compile "io.springfox:springfox-swagger-ui:2.7.0"
````


## HAL Browser

http://localhost:8080/hal-browser/browser.html

## Spring Actuator

http://localhost:8080/actuator

HTTP baseAuth, user=actuator, password=actuator

## Spring Boot Admin

http://codecentric.github.io/spring-boot-admin/1.5.3/


http://localhost:8080






