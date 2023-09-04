package com.example.passman.database;

import com.example.passman.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDBAccess {

    Optional<User> findByID(UUID id);

    Optional<User> findByUsername(String username);

    void updatePassword(Optional<User> user, String newPassword);

    void updateUsername(Optional<User> user, String newName);

    List<User> findPasswordsByJoinFetch(String username);

}
