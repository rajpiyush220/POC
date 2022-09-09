# Getting Started

### Custom banner with spring boot

- Generate Banner [Here](https://springhow.com/spring-boot-banner-generator/)
- Copy content and paste in resources folder with name *'banner.txt'* (This is default location and file name)
- To use custom location change below properties accordingly
```properties
    spring.banner.location=<Banner location> # For text banner
    spring.banner.image.location=<Banner image location> # For image banner
```
- To turn off banner use below properties
```properties
	spring.main.banner-mode=off
```
