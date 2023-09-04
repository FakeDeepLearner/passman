package com.example.passman.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
            name = "username",
            nullable = false,
            unique = true
    )
    @NonNull
    private String username;


    /**
     * The hashed password, not the actual one.
     * This is the password that the user has to the application itself
     * */
    @Column(
            name = "password",
            nullable = false
    )
    @NonNull
    private String password;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserPasswordPair> passwordPairs;


}
