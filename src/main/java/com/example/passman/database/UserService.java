package com.example.passman.database;

import com.example.passman.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public Optional<User> findByID(UUID id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT FROM users WHERE id=:idParam", User.class);
        query.setParameter("idParam", id);
        return Optional.ofNullable(query.getSingleResult());

    }

    @Override
    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT FROM users WHERE username=:usernameParam", User.class
        );
        query.setParameter("usernameParam", username);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    @Transactional
    public void updatePassword(Optional<User> user, String newPassword) {
        if(user.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(newPassword);
            user.get().setPassword(encodedPassword);
            entityManager.merge(user);
        }
    }

    @Override
    @Transactional
    public void updateUsername(Optional<User> user, String newName) {
        if(user.isPresent()){
            user.get().setUsername(newName);
            entityManager.merge(user);
        }
    }

    @Override
    public List<User> findPasswordsByJoinFetch(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM users u " +
                "JOIN FETCH u.passwords " +
                        "WHERE u.username=:nameParam", User.class);
        query.setParameter("nameParam", username);
        return query.getResultList();

    }
}
