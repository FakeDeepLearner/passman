package com.example.passman.controllers;

import com.example.passman.database.Service.UserService;
import com.example.passman.entities.LoginForm;
import com.example.passman.entities.SignupForm;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean signUpUser(@RequestBody SignupForm signupForm){
        return userService.signUpUser(signupForm.username(), signupForm.password(), signupForm.email());
    }

    @PostMapping("/passman/login")
    public boolean logInUser(@RequestBody LoginForm loginForm){
        return userService.logInUser(loginForm.usernameOrEmail(), loginForm.password());
    }
}
