package com.example.passman.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@Data
@NoArgsConstructor

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
     *
     * */
    @Column(
            name = "password",
            nullable = false
    )
    @NonNull
    private String password;


}
