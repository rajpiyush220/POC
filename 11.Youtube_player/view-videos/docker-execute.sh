#!/bin/sh
docker-compose stop
docker-compose rm -f
docker-compose pull
docker images | grep none | awk '{ print $3; }' | xargs docker rmi
docker-compose up -d