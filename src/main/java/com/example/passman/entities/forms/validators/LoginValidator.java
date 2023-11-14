package com.example.passman.entities.forms.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

public class LoginValidator implements ConstraintValidator<UsernameOrEmailConstraint, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return isValidEmail(s) || s.matches("^(?=[^@.,<>'&=+\\-_]{8,}$).*$");
    }

    private boolean isValidEmail(String s){
        EmailValidator emailValidator = new EmailValidator();
        return emailValidator.isValid(s, null);
    }


}
