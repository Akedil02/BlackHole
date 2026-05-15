package org.example.blackholetourismagencybook.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.lang.reflect.Type;

@Entity
@Table(name = "users")
@Data
public class User {

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status = UserStatus.PENDING_QUESTIONNAIRE;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;
}
