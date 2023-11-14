package com.example.passman.entities.forms;

import com.example.passman.entities.forms.validators.UsernameOrEmailConstraint;

public record LoginForm(@UsernameOrEmailConstraint String usernameOrEmail, String password) {
}
