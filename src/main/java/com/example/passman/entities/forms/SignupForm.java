package com.example.passman.entities.forms;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record SignupForm(@NotBlank(message = "Username can't be empty") String username,
                         @Email String email,
                         @SignUpPasswordConstraint String password)
        implements ConstraintValidator<SignUpPasswordConstraint, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
         String matchingRegex = "String regex = \"^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])" +
                 "(?=.*[!@#$%^&*()_+|~=\\\\-`{}\\\\[\\\\]:\\\";'<>?,./])\" +\n" +
                 "\"[A-Za-z0-9!@#$%^&*()_+|~=\\\\-`{}\\\\[\\\\]:\\\";'<>?,./]{8,}$\";\n";
         Pattern pattern = Pattern.compile(matchingRegex);
         Matcher matcher = pattern.matcher(s);
         return matcher.matches();
    }
}
