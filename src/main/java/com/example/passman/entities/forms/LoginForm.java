package com.example.passman.entities.forms;

import com.example.passman.entities.forms.validators.UsernameOrEmailConstraint;

public record LoginForm(@UsernameOrEmailConstraint String usernameOrEmail, String password) {

    //Since the username can't contain @, and any email address entered must be valid, the input containing @ will
    //definitively be an email address that we can look up.
    public boolean isInputEmail(){
        return usernameOrEmail.contains("@");
    }
}
