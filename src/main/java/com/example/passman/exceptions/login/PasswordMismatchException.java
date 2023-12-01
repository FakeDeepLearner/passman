package com.example.passman.exceptions.login;

import com.example.passman.entities.forms.LoginForm;

public class PasswordMismatchException extends IllegalArgumentException{
    private final LoginForm badForm;


    public PasswordMismatchException(LoginForm badForm, String exceptionMessage){
        super("Wrong password");
        this.badForm = badForm;
    }

    public LoginForm getBadForm() {
        return badForm;
    }

}

