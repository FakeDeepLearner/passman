package com.example.passman.exceptions.login;

import com.example.passman.entities.forms.LoginForm;

public class InvalidEmailException extends IllegalArgumentException{

    private final LoginForm badForm;


    public InvalidEmailException(LoginForm badForm, String exceptionMessage){
        super("Wrong email address, please try again");
        this.badForm = badForm;
    }

    public LoginForm getBadForm() {
        return badForm;
    }


}
