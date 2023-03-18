#!/bin/sh
echo "Cleaning and rebuilding Jar"
./gradlew clean build
cat cred.txt | docker login --username devtouchblankspot --password-stdin
echo "Building latest Docker image"
docker build -t devtouchblankspot/player:latest .
echo "Pushing Latest docker image to docker hub"
docker push devtouchblankspot/player:latest