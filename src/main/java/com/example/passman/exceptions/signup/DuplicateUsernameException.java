package com.example.passman.exceptions.signup;


import com.example.passman.entities.forms.SignupForm;

public class DuplicateUsernameException extends IllegalArgumentException {
    private final SignupForm badForm;

    private final String exceptionMessage;
    public DuplicateUsernameException(SignupForm badForm, String exceptionMessage){
        super("Username is already taken");
        this.badForm = badForm;
        this.exceptionMessage = exceptionMessage;
    }

    public SignupForm getBadForm() {
        return badForm;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
