package com.example.passman.exceptions.signup;

import com.example.passman.entities.forms.SignupForm;

public class DuplicateEmailException extends IllegalArgumentException{

    private final SignupForm badForm;

    private final String exceptionMessage;

    public DuplicateEmailException(SignupForm form, String exceptionMessage){
        super("The email address is already taken");
        badForm = form;
        this.exceptionMessage = exceptionMessage;
    }

    public SignupForm getBadForm() {
        return badForm;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
