package com.touchblankspot.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EqualFieldsValidator.class})
public @interface EqualFields {

  String message();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String baseField();

  String matchField();
}
