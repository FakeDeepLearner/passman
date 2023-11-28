package com.example.passman.exceptions.login;

import com.example.passman.entities.forms.LoginForm;

public class PasswordMismatchException extends IllegalArgumentException{
    private final LoginForm badForm;

    private final String exceptionMessage;

    public PasswordMismatchException(LoginForm badForm, String exceptionMessage){
        super("Wrong password");
        this.badForm = badForm;
        this.exceptionMessage = exceptionMessage;
    }

    public LoginForm getBadForm() {
        return badForm;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}

