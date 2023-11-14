package com.example.passman.entities.forms.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<SignUpUsernameConstraint, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("^(?=[^@.,<>'&=+\\-_]{8,}$).*$");
    }

}
