package com.example.passman.controllers;

import com.example.passman.database.Service.UserService;
import com.example.passman.entities.forms.LoginForm;
import com.example.passman.entities.forms.SignUpReturnType;
import com.example.passman.entities.forms.SignupForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginAndSignupController {
    private final UserService userService;

    @Autowired
    public LoginAndSignupController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/passman/signup")
    public ResponseEntity<SignUpReturnType> signUpUser(@Valid @RequestBody SignupForm signupForm){
        userService.signUpUser(signupForm);
        return ResponseEntity.status(204).build();

    }

    @PostMapping("/passman/login")
    public boolean logInUser(@RequestBody LoginForm loginForm){
        return userService.logInUser(loginForm.usernameOrEmail(), loginForm.password());
    }
}
