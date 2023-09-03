package com.example.passman.entities;

import jakarta.persistence.*;
import org.springframework.data.web.JsonPath;

import java.util.UUID;

@Entity
@Table(name = "users-passwords")
public class UserPasswordPair {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String websiteUrl;

    //Again, this is the hashed password to the website
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
                                                CascadeType.DETACH,
                                                CascadeType.PERSIST,
                                                CascadeType.REFRESH})
    @JoinColumns({
            @JoinColumn(name = "username", referencedColumnName = "username"),
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    })
    private User user;

}
