package com.example.miapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    // Constructor práctico
    public Boleta(List<Producto> productos) {
        this.fecha = new Date();
        this.productos = productos;
        this.total = productos.stream().mapToDouble(Producto::getPrecio).sum();
    }
}
