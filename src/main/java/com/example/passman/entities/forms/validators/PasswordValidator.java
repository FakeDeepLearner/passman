package com.example.passman.entities.forms.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<SignUpPasswordConstraint, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String matchingRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+|~=\\-`{}\\[\\]:\";'<>?,./])" +
                "[A-Za-z0-9!@#$%^&*()_+|~=\\-`{}\\[\\]:\";'<>?,./]{8,}$";
        return s.matches(matchingRegex);
    }
}
