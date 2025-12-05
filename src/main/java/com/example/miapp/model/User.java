package com.example.miapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String password;
}

