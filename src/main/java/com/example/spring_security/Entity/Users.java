package com.example.spring_security.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String ismi;

    @Column(nullable = false)
    private String familyasi;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String parol;
}
