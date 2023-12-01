package com.example.passman.exceptions.signup;


import com.example.passman.entities.forms.SignupForm;

public class DuplicateUsernameException extends IllegalArgumentException {
    private final SignupForm badForm;

    public DuplicateUsernameException(SignupForm badForm, String exceptionMessage){
        super("Username is already taken");
        this.badForm = badForm;
    }

    public SignupForm getBadForm() {
        return badForm;
    }

}
