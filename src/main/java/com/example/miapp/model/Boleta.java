package com.example.miapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boletas")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    private Double total;

    @ManyToMany
    @JoinTable(
            name = "boleta_producto",
            joinColumns = @JoinColumn(name = "boleta_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos;

    // Constructor práctico opcional
    public Boleta(Double total, List<Producto> productos) {
        this.total = total;
        this.productos = productos;
        this.fecha = new Date();
    }
}
