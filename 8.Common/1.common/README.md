# Common Library

### Steps to use it
* Few properties need to be change in pom.xml, please check it and update accordingly and if you want you can update library version in [build.gradle](https://github.com/OurVirtualLibrary/common/blob/develop/build.gradle) and [pom.xml](https://github.com/OurVirtualLibrary/common/blob/develop/pom.xml)
* Execute below command to publish it to local repository
```shell
    ./gradlew publishToMavenLocal
    mvn clean deploy  (This can be used once deploying from project root directory)
    mvn clean deploy  -f /path_of/pom.xml
```
* Create a project with dependecy management and add below package as dependency
    ##### For gradle dependency manager add below code block
        implementation 'com.touchblankspot:common:1.0'
    ##### For maven dependency manager
        <dependency>
            <groupId>com.touchblankspot</groupId>
            <artifactId>common</artifactId>
            <version>1.0</version>
        </dependency>

### TODO
    * Add auto version increment features if possible
    * Publish it to maven central/JFrog or some other artifactory
