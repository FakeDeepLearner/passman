package com.example.passman.entities.forms;

import com.example.passman.entities.forms.validators.SignUpPasswordConstraint;
import com.example.passman.entities.forms.validators.SignUpUsernameConstraint;
import jakarta.validation.constraints.Email;


public record SignupForm(@SignUpUsernameConstraint String username,
                         @Email(message = "The email address must be valid") String email,
                         @SignUpPasswordConstraint String password)
{

}
