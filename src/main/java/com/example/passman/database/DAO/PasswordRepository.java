package com.example.passman.database.DAO;

import com.example.passman.entities.User;
import com.example.passman.entities.UserPasswordPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PasswordRepository extends JpaRepository<UserPasswordPair, UUID> {

    public List<UserPasswordPair> findAllPasswordsOf(User user);

    public boolean userHasPasswordFor(User user, String websiteUrl);

    Optional<UserPasswordPair> findPasswordOf(User user, String url);

    void updatePasswordTo(User user, String url, String newPassword);


}
