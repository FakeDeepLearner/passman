package com.example.passman.entities.forms;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = SignupForm.class)
public @interface SignUpPasswordConstraint {
    String message() default "The password must contain at least 8 characters, at least one number" +
            ", and at least one uppercase and lowercase letter";

}
