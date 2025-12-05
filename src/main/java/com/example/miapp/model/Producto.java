package com.example.miapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String categoria;
    private Double precio;
    private String imagenUrl;

}

