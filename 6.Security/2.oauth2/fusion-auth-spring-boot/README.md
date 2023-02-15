
# Example: Using Spring with FusionAuth

The application demonstrates how to integrate FusionAuth into your spring rest application.

This app explains only rest api. To get more details on MVC app [please check Git](https://github.com/FusionAuth/fusionauth-example-java-spring)  and [Blog](https://fusionauth.io/blog/2023/01/03/spring-and-fusionauth)

## Prerequisites
You will need the following things properly installed on your computer.

* [Git](http://git-scm.com/): Presumably you already have this on your machine if you are looking at this project locally; if not, use your platform's package manager to install git, and `git clone` this repo.
* [Java](https://www.oracle.com/java/technologies/downloads/): Java can be installed via a variety of methods
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.2/gradle-plugin/reference/html/)
* [Docker](https://www.docker.com): For standing up FusionAuth from within a Docker container. (You can [install it other ways](https://fusionauth.io/docs/v1/tech/installation-guide/), but for this example you'll need Docker.)


## FusionAuth Configuration

<font color='red'>**In this documentation I did not explain about fusionauth.**</font>

* Use [Fusion auth Official Doc](https://fusionauth.io/docs/v1/tech/) for more details.

This example assumes that you will run FusionAuth from a Docker container. In the root of this project directory (next to this README) are two files [a Docker compose file](./docker-compose.yml) and an [environment variables configuration file](./.env). Assuming you have Docker installed on your machine, a `docker-compose up` will bring FusionAuth up on your machine.

The FusionAuth configuration files also make use of a unique feature of FusionAuth, called Kickstart: when FusionAuth comes up for the first time, it will look at the [Kickstart file](./kickstart/kickstart.json) and mimic API calls to configure FusionAuth for use. It will perform all the necessary setup to make this demo work correctly, but if you are curious as to what the setup would look like by hand, the "FusionAuth configuration (by hand)" section of this README describes it in detail.

For now, get FusionAuth in Docker up and running (via `docker-compose up`) if it is not already running; to see, [click here](http://localhost:9011/) to verify it is up and running.

> **NOTE**: If you ever want to reset the FusionAuth system, delete the volumes created by docker-compose by executing `docker-compose down -v`. FusionAuth will only apply the Kickstart settings when it is first run (e.g., it has no data configured for it yet).


## Running / Development

* `mvn spring-boot:run`
* [Open a browser](http://localhost:8080) and log in using our user's credentials ("richard@example.com"/"password").

You can also register a new user for this application and will be automatically logged in after.

Log into the [FusionAuth admin screen](http://localhost:9011) with a different browser or incognito window using the admin user credentials ("admin@example.com"/"password") to explore the admin user experience.

## Changing The FusionAuth Instance

The default values are from the Kickstart file. If you want to point at a different FusionAuth instance, modify:

Replace these values in `src/main/resources/application.properties` :

- the client Id
- the client secret
- the base URL your FusionAuth instance is running on.

and in `src/main/resources/templates/user.html`

- the client Id
- the base URL your FusionAuth instance is running on. 

## Google java code format
* Run below command to check and fix format error
    * To check format issue :- ./gradlew goJF(On linux) gradlew goJF(on windows)
    * To correct format issue :- ./gradlew spotlessApply(On linux) gradlew spotlessApply(on windows)


#### Execution Steps
- Change server port if required
- Run below command to execute it you want to use command line otherwise use any IDE
```shell
    ./gradlew bootrun
```
- If you don't want to use command then run as Spring boot app
- [Swagger-UI](http://localhost:8081/swagger-ui/index.html) link *Change port number if required*

## References:
* [Secure Rest Api Blog](https://blog.devgenius.io/rest-api-with-oidc-spring-and-fusionauth-f8a7915e4d06)
* [Fusion Auth API](https://fusionauth.io/docs/v1/tech/apis/)