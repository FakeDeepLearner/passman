package com.example.passman.database.Service;

import com.example.passman.database.DAO.UserRepository;
import com.example.passman.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
        try{
            return Optional.ofNullable(query.getSingleResult());
        }
        catch (NoResultException exception){
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email=:emailParam", User.class
        );
        query.setParameter("emailParam", email);
        try{
            return Optional.ofNullable(query.getSingleResult());
        }
        catch (NoResultException exception){
            return Optional.empty();
        }
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

    @Override
    public boolean signUpUser(String name, String password, String email) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        User newUser = new User(name, encodedPassword, email);
        entityManager.persist(newUser);
        return true;
    }

    @Override
    public boolean logInUser(String nameOrEmail, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<User> foundUserUsername = findByUsername(nameOrEmail);
        Optional<User> foundUserEmail = findByEmail(nameOrEmail);
        if (foundUserUsername.isEmpty() && foundUserEmail.isEmpty()){
            return false;
        } else if (foundUserUsername.isEmpty()) {
            User foundUser = foundUserEmail.get();
            String encodedPassword = encoder.encode(password);
            return foundUser.getPassword().equals(encodedPassword);
        }
        else if (foundUserEmail.isEmpty()) {
            User foundUser = foundUserUsername.get();
            String encodedPassword = encoder.encode(password);
            return foundUser.getPassword().equals(encodedPassword);
        }
        return false;
    }
}
