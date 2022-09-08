#!/bin/bash
# Build jar files and docker images
if [ -z "$1" ]; then
    echo "Supply push to docker hub argument as true/false like build_deploy.sh true"
else
	./gradlew clean bootjar
	docker build . -t touchblankspot/sample-boot-docker
	if [ $1 == true ]
	then
		echo "Pushing touchblankspot/sample-boot-docker to docker hub"
		docker push docker.io/touchblankspot/sample-boot-docker
	fi

	docker-compose down
	docker-compose up -d
fi
