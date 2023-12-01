package com.example.passman.controllers;


import com.example.passman.entities.forms.LoginForm;
import com.example.passman.entities.forms.LoginReturnType;
import com.example.passman.exceptions.login.InvalidEmailException;
import com.example.passman.exceptions.login.InvalidUsernameException;
import com.example.passman.exceptions.login.PasswordMismatchException;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LoginExceptionHandler {

    @ExceptionHandler(PasswordMismatchException.class)
    protected ResponseEntity<LoginReturnType> handlePasswordMismatch(@NonNull PasswordMismatchException exception){
        LoginForm form = exception.getBadForm();
        HttpHeaders unauthorizedHeaders = new HttpHeaders();
        unauthorizedHeaders.add("WWW-Authenticate", "Basic realm=Logging In");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(unauthorizedHeaders).body(new LoginReturnType(
                form.usernameOrEmail(), form.password(), exception.getMessage()
        ));

    }

    @ExceptionHandler(InvalidEmailException.class)
    protected ResponseEntity<LoginReturnType> handleInvalidEmail(@NonNull InvalidEmailException exception){
        LoginForm form = exception.getBadForm();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LoginReturnType(
                form.usernameOrEmail(), form.password(), exception.getMessage()
        ));

    }

    @ExceptionHandler(InvalidUsernameException.class)
    protected ResponseEntity<LoginReturnType> handleInvalidUsername(@NonNull InvalidUsernameException exception){
        LoginForm form = exception.getBadForm();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LoginReturnType(
                form.usernameOrEmail(), form.password(), exception.getMessage()
        ));

    }


}
