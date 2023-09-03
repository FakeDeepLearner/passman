package com.example.passman.database;

import com.example.passman.entities.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDBAccess {

    public User findByID(UUID id);

    public User findByUsername(String username);

    public void updatePassword(User user, String newPassword);

    public void updateUsername(User user, String newName);

}
