# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Flyway Migration](https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle/#howto.data-initialization.migration-tool.flyway)

### Guides

#### Execution Steps
- Update db related information in [application.yml](https://github.com/keepmoving-motivateyourself/POC/blob/main/6.Security/1.spring-security-jwt/src/main/resources/application.yml)
- Change server port if required
- Run below command to execute it you want to use command line otherwise use any IDE
```shell
    ./gradlew bootrun
```
- Check [postman](https://github.com/keepmoving-motivateyourself/POC/tree/main/6.Security/1.spring-security-jwt/postman-collection/) directory for postman collection
- [Swagger-UI](http://localhost:8081/swagger-ui/index.html) link *Change port number if required*
- Check [logs](https://github.com/keepmoving-motivateyourself/POC/tree/main/6.Security/1.spring-security-jwt/logs)  in this directory
