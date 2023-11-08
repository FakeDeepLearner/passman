package com.example.passman.controllers;

import com.example.passman.database.Service.UserService;
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
    public void signUpUser(@RequestBody SignupForm signupForm){
        userService.signUpUser(signupForm.username(), signupForm.password(), signupForm.email());
    }
}
