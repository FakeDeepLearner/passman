package com.example.passman.exceptions.login;

import com.example.passman.entities.forms.LoginForm;

public class InvalidEmailException extends IllegalArgumentException{

    private final LoginForm badForm;

    private final String exceptionMessage;

    public InvalidEmailException(LoginForm badForm, String exceptionMessage){
        super("Wrong email address, please try again");
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
