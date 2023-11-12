package com.example.passman.entities.forms;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record SignupForm(@NotBlank(message = "Username can't be empty") String username,
                         @Email(message = "The email address must be valid") String email,
                         @SignUpPasswordConstraint String password)
        implements ConstraintValidator<SignUpPasswordConstraint, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
         String matchingRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+|~=\\-`{}\\[\\]:\";'<>?,./])" +
                 "[A-Za-z0-9!@#$%^&*()_+|~=\\-`{}\\[\\]:\";'<>?,./]{8,}$";
         Pattern pattern = Pattern.compile(matchingRegex);
         Matcher matcher = pattern.matcher(s);
         return matcher.matches();
    }
}
