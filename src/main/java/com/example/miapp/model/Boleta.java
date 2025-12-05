package com.example.miapp.model;

import jakarta.persistence.*;
import lombok.*;
import com.example.miapp.model.User;

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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;  

    // Constructor práctico opcional
    public Boleta(Double total, List<Producto> productos, User usuario) {
        this.total = total;
        this.productos = productos;
        this.usuario = usuario;
        this.fecha = new Date();
    }
}
