package com.example.passman.database;

import com.example.passman.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDBAccess{

    private final EntityManager entityManager;

    @Autowired
    public UserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByID(UUID id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT FROM users WHERE id=:idParam", User.class);
        query.setParameter("idParam", id);
        Optional<User> returnedUser = Optional.ofNullable(query.getSingleResult());
        return returnedUser.orElse(null);

    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT FROM users WHERE username=:usernameParam", User.class
        );
        query.setParameter("usernameParam", username);
        Optional<User> returnedUser = Optional.ofNullable(query.getSingleResult());
        return returnedUser.orElse(null);
    }

    @Override
    @Transactional
    public void updatePassword(User user, String newPassword) {
        if(user != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(newPassword);
            user.setPassword(encodedPassword);
            entityManager.merge(user);
        }
    }

    @Override
    @Transactional
    public void updateUsername(User user, String newName) {
        if(user != null){
            user.setUsername(newName);
            entityManager.merge(user);
        }
    }
}
