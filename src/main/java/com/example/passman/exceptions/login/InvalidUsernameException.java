package com.example.passman.exceptions.login;

import com.example.passman.entities.forms.LoginForm;

public class InvalidUsernameException extends IllegalArgumentException{

    private final LoginForm badForm;

    private final String exceptionMessage;

    public InvalidUsernameException(LoginForm badForm, String exceptionMessage){
        super("Wrong username, please try again");
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

