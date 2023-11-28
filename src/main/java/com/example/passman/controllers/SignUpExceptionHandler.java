package com.example.passman.controllers;

import com.example.passman.entities.forms.SignUpReturnType;
import com.example.passman.entities.forms.SignupForm;
import com.example.passman.exceptions.signup.DuplicateEmailException;
import com.example.passman.exceptions.signup.DuplicateUsernameException;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SignUpExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    protected ResponseEntity<SignUpReturnType> handleDuplicateEmail(@NonNull DuplicateEmailException exception){
        SignupForm form = exception.getBadForm();
        return ResponseEntity.status(409).
                body(new SignUpReturnType(form.username(), form.password(),
                        form.email(), exception.getExceptionMessage()));
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    protected ResponseEntity<SignUpReturnType> handleDuplicateUsername(@NonNull DuplicateUsernameException exception) {
        SignupForm form = exception.getBadForm();
        return ResponseEntity.status(409).
                body(new SignUpReturnType(form.username(), form.password(),
                        form.email(), exception.getExceptionMessage()));
    }
}
