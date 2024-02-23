package com.example.passman.controllers;

import com.example.passman.database.Service.PasswordService;
import com.example.passman.database.Service.UserService;
import com.example.passman.entities.User;
import com.example.passman.entities.UserPasswordPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class HomePageController {
    private final PasswordService passwordService;

    private final UserService userService;

    @Autowired
    public HomePageController(PasswordService passwordService, UserService userService) {
        this.passwordService = passwordService;
        this.userService = userService;
    }


    @GetMapping("/{userID}/home")
    public ResponseEntity<List<UserPasswordPair>> loadHomePageContent(@PathVariable UUID userID) {
        Optional<User> userOptional = userService.findById(userID);
        return userOptional.map(user ->
                        ResponseEntity.ok(passwordService.findAllPasswordsOf(user))).
                orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/addpassword")
    public ResponseEntity addPassword(@RequestParam UUID userID, @RequestParam String newPassword,
                                        @RequestParam String websiteUrl){
        userService.addNewPassword(userID, websiteUrl, newPassword);
        return ResponseEntity.ok().build();
    }

}
