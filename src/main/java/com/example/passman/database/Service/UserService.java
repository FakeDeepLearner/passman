package com.example.passman.database.Service;

import com.example.passman.entities.User;
import com.example.passman.entities.forms.LoginForm;
import com.example.passman.entities.forms.SignupForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {
    Optional<User> findById(UUID id);
    Optional<String> getUsernameById(UUID id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    @Transactional
    void updatePassword(Optional<User> user, String newPassword);

    @Transactional
    void updateUsername(Optional<User> user, String newName);

    List<User> findPasswords(String username);

    @Transactional
    boolean checkUserSignup(SignupForm signupForm);

    boolean checkUserLogin(LoginForm loginForm);
}
