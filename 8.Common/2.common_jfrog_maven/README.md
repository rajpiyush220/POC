# Common Library

### Configuration to publish it on JFrog artifactory
  * [Create a Jfrog free tier account](https://jfrog.com/start-free/)
  * Watch this [setup video](https://www.youtube.com/watch?v=CqvVQkOSGTA&ab_channel=JFrog) to configure publish artifactory and related information
  * Make sure to follow all the steps carefully while following steps explained in the project
  * Look for the changes made in [pom.xml](https://github.com/OurVirtualLibrary/common/blob/feature/jfrog_maven/pom.xml#L46-L57)
### Steps to use it
* Few properties need to be change in pom.xml, please check it and update accordingly and if you want you can update library version in [build.gradle](https://github.com/OurVirtualLibrary/common/blob/develop/build.gradle) and [pom.xml](https://github.com/OurVirtualLibrary/common/blob/develop/pom.xml)
* Execute below command to publish it to local repository
```shell
    mvn clean deploy  (This can be used once deploying from project root directory)
    mvn clean deploy  -f /path_of/pom.xml
```
* Create a project with dependecy management and add below package as dependency
    ##### To use it in gradle please refer gradle branch
    ##### For maven dependency manager
        <dependency>
            <groupId>com.touchblankspot</groupId>
            <artifactId>common</artifactId>
            <version>1.0</version>
        </dependency>

### TODO
    * Add auto version increment features if possible
    
