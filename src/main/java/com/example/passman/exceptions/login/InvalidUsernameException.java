package com.example.passman.exceptions.login;

import com.example.passman.entities.forms.LoginForm;

public class InvalidUsernameException extends IllegalArgumentException{

    private final LoginForm badForm;


    public InvalidUsernameException(LoginForm badForm, String exceptionMessage){
        super("Wrong username, please try again");
        this.badForm = badForm;
    }

    public LoginForm getBadForm() {
        return badForm;
    }


}

