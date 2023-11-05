package com.example.passman.database.DAO;

import com.example.passman.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    void updatePassword(Optional<User> user, String newPassword);

    void updateUsername(Optional<User> user, String newName);

    List<User> findPasswords(String username);
}