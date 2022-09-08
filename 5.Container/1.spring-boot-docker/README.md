# Getting Started

## spring-boot-docker

### Prerequisite
- <span style="color:red">Docker and docker-compose should be installed</span>
- Docker id required to push it to docker hub otherwise you can avoid pushing it to hub

### How to run it
- Using any standard IDE
- Using gradle command
```shell
    # To run it on console
    ./gradlew spring-boot-docker:bootRun
    # Below command will do following task
      # Build executable jar
      # Build docker image with tag name touchblankspot/sample-boot-docker (you can change name as per you convenient)
      # Push it docker hub if supplied arguement is true (For this docker should be logged in)
    # ./build_deploy.sh true/false
```
- [Access sample endpoint](http://localhost:8080/hello)

