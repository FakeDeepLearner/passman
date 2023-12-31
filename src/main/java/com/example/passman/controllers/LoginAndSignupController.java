package com.example.passman.controllers;

import com.example.passman.database.Service.UserService;
import com.example.passman.entities.forms.LoginForm;
import com.example.passman.entities.forms.SignUpReturnType;
import com.example.passman.entities.forms.SignupForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginAndSignupController {
    private final UserService userService;
    @Autowired
    public LoginAndSignupController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void trimWhitespace(WebDataBinder binder){
        binder.registerCustomEditor(SignupForm.class, "username", new StringTrimmerEditor(true));
        binder.registerCustomEditor(SignupForm.class, "email", new StringTrimmerEditor(true));
        binder.registerCustomEditor(SignupForm.class, "password", new StringTrimmerEditor(true));

    }
    @PostMapping("/passman/signup")
    public ResponseEntity<SignUpReturnType> checkUserSignUp(@Valid @RequestBody SignupForm signupForm){
        if(userService.checkUserSignup(signupForm)) {
            userService.createNewUser(signupForm);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @PostMapping("/passman/login")
    public ResponseEntity<SignUpReturnType> checkUserLogin(@Valid @RequestBody LoginForm loginForm) {
        if (userService.checkUserLogin(loginForm)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
