package com.touchblankspot.auth.jwt.annotations;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@RestController
public @interface JWTAuthRestController {}
