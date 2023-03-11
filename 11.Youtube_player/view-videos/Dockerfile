FROM openjdk:17-alpine

LABEL maintainer="rajpiyush220@gmail.com"

WORKDIR /workspace/app

COPY build/libs/view-videos-1.0.jar ./auto_play.jar

ENTRYPOINT [ "java", "-jar" , "/workspace/app/auto_play.jar" ]
