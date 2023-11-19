package com.example.passman.controllers;

import com.example.passman.entities.forms.FormReturnType;
import com.example.passman.entities.forms.SignupForm;
import com.example.passman.exceptions.signup.DuplicateEmailException;
import com.example.passman.exceptions.signup.DuplicateUsernameException;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(DuplicateEmailException.class)
    protected ResponseEntity<FormReturnType> handleDuplicateEmail(@NonNull DuplicateEmailException exception){
        SignupForm form = exception.getBadForm();
        HttpHeaders headers = new HttpHeaders();
        headers.add("email-taken", "Username is already taken");
        return ResponseEntity.status(409).headers(headers).
                body(new FormReturnType(form.username(), form.password(), form.email()));
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    protected ResponseEntity<FormReturnType> handleDuplicateUsername(@NonNull DuplicateUsernameException exception){
        SignupForm form = exception.getBadForm();
        HttpHeaders headers = new HttpHeaders();
        headers.add("username-taken", "Email is already taken");
        return ResponseEntity.status(409).headers(headers).
                body(new FormReturnType(form.username(), form.password(), form.email()));
    }
}
