package com.example.passman.database.Service;

import com.example.passman.database.DAO.PasswordRepository;
import com.example.passman.entities.User;
import com.example.passman.entities.UserPasswordPair;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordServiceImpl implements PasswordService {

    private final EntityManager entityManager;
    private final PasswordRepository passwordRepository;

    @Autowired
    public PasswordServiceImpl(EntityManager entityManager, PasswordRepository passwordRepository) {
        this.entityManager = entityManager;
        this.passwordRepository = passwordRepository;
    }


    @Override
    public Optional<String> getPasswordById(UUID id) {
        Optional<UserPasswordPair> pair = findById(id);
        return pair.map(UserPasswordPair::getPassword);
    }



    @Override
    public List<UserPasswordPair> findAllPasswordsOf(User user) {
        TypedQuery<UserPasswordPair> query = entityManager.createQuery("SELECT p FROM UserPasswordPair p " +
                                                                    "WHERE p.user.id=:data",
                UserPasswordPair.class);
        query.setParameter("data", user.getId());
        return query.getResultList();

    }


    @Override
    public boolean userHasPasswordFor(User user, String websiteUrl) {
        TypedQuery<UserPasswordPair> query = entityManager.createQuery("SELECT p FROM UserPasswordPair p " +
                                                                    "WHERE p.user.id=:idParam " +
                                                                    "AND p.websiteUrl=:urlParam",
                UserPasswordPair.class);
        query.setParameter("idParam", user.getId());
        query.setParameter("urlParam", websiteUrl);
        return !query.getResultList().isEmpty();

    }

    @Override
    public Optional<UserPasswordPair> findById(UUID id) {
        return passwordRepository.findById(id);
    }

    @Override
    public Optional<UserPasswordPair> findPasswordOf(User user, String url) {
        TypedQuery<UserPasswordPair> query = entityManager.createQuery(
                "SELECT p FROM UserPasswordPair p " +
                        "WHERE p.user.id=:idParam " +
                        "AND p.websiteUrl=:urlParam"
        , UserPasswordPair.class);
        query.setParameter("idParam", user.getId());
        query.setParameter("urlParam", url);

        try{
            return Optional.ofNullable(query.getSingleResult());
        }
        catch(NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public void updatePasswordTo(User user, String url, String newPassword) {
        Optional<UserPasswordPair> queryResult = findPasswordOf(user, url);
        if(queryResult.isPresent()){
            UserPasswordPair passwordPair = queryResult.get();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(newPassword);
            passwordPair.setPassword(encodedPassword);
            entityManager.merge(passwordPair);
        }
    }
}
