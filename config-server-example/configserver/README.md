# Getting Started

### Reference Documentation
For further reference, please consider the following sections:
### Tech stack
    * Spring boot
    * Spring Cloud config server
    * GitHub as properties holder

### Guides

The following guides illustrate how to use some features concretely:

* [Centralized Configuration](https://spring.io/guides/gs/centralized-configuration/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)


### Steps to execute it
    
* Change following properties in application.yml
```yaml
  spring:
    cloud:
      config:
        server:
          git:
            uri: https://github.com/keepmoving-motivateyourself/config-repo.git
            # uri -> Specify public repository which hold env specific properties file for project
            default-label: develop
            # default-label --> You can keep your default branch name here
  server:
    port: '8071'
    # port --> change it accordingly but make same should be used in config client otherwise it wont
    # be able to pull the config    
```
* run this app using IDE or ```./gradlew/gradlew bootRun```