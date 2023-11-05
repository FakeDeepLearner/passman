package com.example.passman.database;

import com.example.passman.database.Service.UserService;
import com.example.passman.entities.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
public class UserTests {

    private final EntityManager entityManager;
    private final com.example.passman.database.Service.UserService userService;

    @Autowired
    public UserTests(EntityManager entityManager, UserService userService) {
        this.entityManager = entityManager;
        this.userService = userService;
    }


    @Test
    @Transactional
    public void addTest(){
        User user = new User("erenaydin", "passcode");
        Assertions.assertDoesNotThrow(() -> entityManager.persist(user));
        Assertions.assertDoesNotThrow(() -> entityManager.remove(user));
    }

    @Test
    @Transactional
    public void duplicateUsernamesErrorTest(){
        User user1 = new User("erenaydin", "passcode");
        entityManager.persist(user1);
        User user2 = new User("erenaydin", "password");
        Assertions.assertThrows(TransactionSystemException.class, () -> entityManager.persist(user2));
    }

    @Test
    @Transactional
    public void findByUsernameTest(){
        User user = new User("erenaydin", "passcode");
        Assertions.assertDoesNotThrow(() -> entityManager.persist(user));
        Assertions.assertDoesNotThrow(() -> userService.findByUsername("erenaydin"));
        Optional<User> returnedUser = userService.findByUsername("erenaydin");
        Assertions.assertTrue(returnedUser.isPresent());
        Assertions.assertDoesNotThrow(() -> entityManager.remove(user));
    }


    @Test
    @Transactional
    public void updatePasswordTest(){
        User user = new User("erenaydin", "passcode");
        Assertions.assertDoesNotThrow(() -> entityManager.persist(user));
        Assertions.assertDoesNotThrow(() -> userService.updatePassword(userService.findByUsername("erenaydin"),
                "password"));
        Optional<User> returnedUser = userService.findByUsername("erenaydin");
        Assertions.assertTrue(returnedUser.isPresent() &&
                returnedUser.get().getPassword().equals("password"));
        entityManager.remove(user);

    }



}
