package com.example.passman.database.Service;

import com.example.passman.database.DAO.UserRepository;
import com.example.passman.entities.User;
import com.example.passman.entities.forms.LoginForm;
import com.example.passman.entities.forms.SignupForm;
import com.example.passman.exceptions.login.InvalidEmailException;
import com.example.passman.exceptions.login.InvalidUsernameException;
import com.example.passman.exceptions.login.PasswordMismatchException;
import com.example.passman.exceptions.signup.DuplicateEmailException;
import com.example.passman.exceptions.signup.DuplicateUsernameException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.exception.ConstraintViolationException;
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
    @Transactional
    public boolean checkUserSignup(SignupForm signupForm) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(signupForm.password());
        User newUser = new User(signupForm.username(), encodedPassword, signupForm.email());
        try {
            entityManager.persist(newUser);

        }
        catch(ConstraintViolationException violationException){
            String constraintName = violationException.getConstraintName();
            switch (constraintName) {
                case "uk_username" -> throw new DuplicateUsernameException(signupForm, "Username is already taken");
                case "uk_email" -> throw new DuplicateEmailException(signupForm, "Email is already taken");

            };
        }
        return true;
    }

    @Override
    public boolean checkUserLogin(LoginForm loginForm) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(loginForm.password());
        Optional<User> foundUser;
        if (loginForm.isInputEmail()) {
            foundUser = findByEmail(loginForm.usernameOrEmail());
            if (foundUser.isEmpty()){
                throw new InvalidEmailException(loginForm, "A user wasn't found with this email address");
            }

        }
        else{
            foundUser = findByUsername(loginForm.usernameOrEmail());
            if (foundUser.isEmpty()){
                throw new InvalidUsernameException(loginForm, "A user wasn't found with this username");
            }
        }

        if (!(foundUser.get().getPassword().equals(hashedPassword))){
            throw new PasswordMismatchException(loginForm, "Incorrect Password");
        }
        return true;
    }
}
