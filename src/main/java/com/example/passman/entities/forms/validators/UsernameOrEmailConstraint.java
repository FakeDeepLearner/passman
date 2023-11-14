package com.example.passman.entities.forms.validators;

import com.example.passman.entities.forms.validators.LoginValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = LoginValidator.class)
public @interface UsernameOrEmailConstraint {
    String message() default "Please provide a valid email address or your username";

}
