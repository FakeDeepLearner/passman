package com.example.passman.database.Service;

import com.example.passman.entities.User;
import com.example.passman.entities.UserPasswordPair;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PasswordService {

    Optional<String> getPasswordById(UUID id);

    List<UserPasswordPair> findAllPasswordsOf(User user);

    boolean userHasPasswordFor(User user, String websiteUrl);

    Optional<UserPasswordPair> findById(UUID id);

    Optional<UserPasswordPair> findPasswordOf(User user, String url);

    void updatePasswordTo(User user, String url, String newPassword);
}
