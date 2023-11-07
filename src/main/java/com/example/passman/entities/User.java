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
            nullable = false
    )
    @NonNull
    private String password;


    @Column(
            nullable = false,
            unique = true
    )
    @NonNull
    private String email;


    @OneToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REMOVE,
            CascadeType.REFRESH
    }, mappedBy = "user")
    private List<UserPasswordPair> passwordPairs;


}
