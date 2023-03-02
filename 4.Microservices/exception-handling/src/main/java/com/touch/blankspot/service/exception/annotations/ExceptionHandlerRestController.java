package com.touch.blankspot.service.exception.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionHandlerRestController {}
