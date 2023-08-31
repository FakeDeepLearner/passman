package com.example.passman.database;

import com.example.passman.entities.User;
import com.example.passman.entities.UserPasswordPair;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PasswordDBAccess {
    public String getPasswordById(UUID id);

    public String getPasswordByUsername(String username);

    public List<UserPasswordPair> findAllPasswordsOf(User user);

    public boolean userHasPasswordFor(User user, String websiteUrl);

    public UserPasswordPair findById(UUID id);


}
