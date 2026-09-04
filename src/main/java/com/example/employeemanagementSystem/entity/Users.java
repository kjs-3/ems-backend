package com.example.employeemanagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(name="username", unique = true)
    private String userName;
    @Column(name="userpassword",nullable = false)
    private String userPassword;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
