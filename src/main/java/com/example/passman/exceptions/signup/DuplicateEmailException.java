package com.example.passman.exceptions.signup;

import com.example.passman.entities.forms.SignupForm;

public class DuplicateEmailException extends IllegalArgumentException{

    private final SignupForm badForm;
    public DuplicateEmailException(SignupForm form){
        super("The email address is already taken");
        badForm = form;
    }

    public SignupForm getBadForm() {
        return badForm;
    }
}
