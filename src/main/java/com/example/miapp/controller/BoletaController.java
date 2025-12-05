package com.example.miapp.controller;

import com.example.miapp.model.Boleta;
import com.example.miapp.model.Producto;
import com.example.miapp.repository.BoletaRepository;
import com.example.miapp.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // Crear boleta con lista de IDs de productos
    @PostMapping
    public Boleta crearBoleta(@RequestBody List<Long> productoIds) {
        List<Producto> productos = productoRepository.findAllById(productoIds);
        Boleta boleta = new Boleta(productos);
        return boletaRepository.save(boleta);
    }

    // Listar todas las boletas
    @GetMapping
    public List<Boleta> listarBoletas() {
        return boletaRepository.findAll();
    }

    // Obtener boleta por ID
    @GetMapping("/{id}")
    public Boleta obtenerBoleta(@PathVariable Long id) {
        return boletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Boleta no encontrada"));
    }
}
