package com.touchblankspot.auth.jwt.springsecurityjwt.annotation;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@RestController
public @interface UserPortalRestController {}
