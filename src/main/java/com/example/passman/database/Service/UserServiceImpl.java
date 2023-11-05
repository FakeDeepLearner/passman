package com.example.passman.database.Service;

import com.example.passman.database.DAO.UserRepository;
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
public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }


    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<String> getUsernameById(UUID id) {
        Optional<User> optionalUser = findById(id);
        return optionalUser.map(User::getUsername);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username=:usernameParam", User.class
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
    public List<User> findPasswords(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u " +
                "JOIN FETCH u.passwordPairs " +
                        "WHERE u.username=:nameParam", User.class);
        query.setParameter("nameParam", username);
        return query.getResultList();

    }
}