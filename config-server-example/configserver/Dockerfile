#Start with a base image containing Java runtime
FROM openjdk:17.0.2-jdk as build

#Information around who maintains the image
MAINTAINER touchblankspot.com

# Add the application's jar to the container
COPY build/libs/configserver-1.0.jar configserver-1.0.jar

#execute the application
ENTRYPOINT ["java","-jar","/configserver-1.0.jar"]