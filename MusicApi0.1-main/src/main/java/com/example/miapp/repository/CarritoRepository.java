package com.example.miapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.miapp.model.Carrito;


@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Carrito findByUserId(Long userId);
}
