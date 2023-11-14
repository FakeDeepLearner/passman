package com.example.passman.entities.forms.validators;


import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UsernameValidator.class)
public @interface SignUpUsernameConstraint {
    String message() default "The username can't contain &, =, <, >, underscore(_), apostrophe('), -, +, comma(,)," +
            "period(.) and the @ symbol; and must be at least 8 characters long";
}
