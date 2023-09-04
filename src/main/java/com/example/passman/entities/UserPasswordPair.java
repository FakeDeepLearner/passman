package com.example.passman.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users-passwords")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor

public class UserPasswordPair {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    @Column(
            name = "url",
            nullable = false
    )
    private String websiteUrl;

    @NonNull
    @Column(
            name = "password",
            nullable = false
    )
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
