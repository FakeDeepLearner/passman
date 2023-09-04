package com.example.passman.database;

import com.example.passman.entities.User;
import com.example.passman.entities.UserPasswordPair;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasswordDBAccess {
    public Optional<String> getPasswordById(UUID id);

    public List<UserPasswordPair> findAllPasswordsOf(User user);

    public boolean userHasPasswordFor(User user, String websiteUrl);

    public Optional<UserPasswordPair> findById(UUID id);

    Optional<UserPasswordPair> findPasswordOf(User user, String url);

    void updatePasswordTo(User user, String url, String newPassword);


}
