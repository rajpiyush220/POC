FROM openjdk:17-alpine

LABEL maintainer="rajpiyush220@gmail.com"

WORKDIR /workspace/app

COPY build/libs/youtube-player-1.0.jar ./player.jar

ENTRYPOINT [ "java", "-jar" , "/workspace/app/player.jar" ]
