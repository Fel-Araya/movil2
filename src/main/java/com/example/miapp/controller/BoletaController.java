package com.example.miapp.controller;

import com.example.miapp.model.Boleta;
import com.example.miapp.model.BoletaRequest;
import com.example.miapp.model.Producto;
import com.example.miapp.model.User;
import com.example.miapp.repository.BoletaRepository;
import com.example.miapp.repository.ProductoRepository;
import com.example.miapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UserRepository usuarioRepository;  // ← nuevo

    @PostMapping
    public ResponseEntity<Boleta> crearBoleta(@RequestBody BoletaRequest request) {

        // 1️⃣ Obtener productos reales desde la BD
        List<Producto> productos = productoRepository.findAllById(request.getProductos());

        // 2️⃣ Obtener usuario por ID
        User usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 3️⃣ Parsear fecha
        Date fecha = new Date();
        if (request.getFecha() != null && !request.getFecha().isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                fecha = sdf.parse(request.getFecha());
            } catch (Exception e) {
                fecha = new Date();
            }
        }

        // 4️⃣ Crear boleta y guardar
        Boleta boleta = new Boleta();
        boleta.setProductos(productos);
        boleta.setTotal(request.getTotal());
        boleta.setFecha(fecha);
        boleta.setUsuario(usuario);

        Boleta boletaGuardada = boletaRepository.save(boleta);

        return ResponseEntity.ok(boletaGuardada);
    }

    @GetMapping
    public ResponseEntity<List<Boleta>> listarBoletas() {
        List<Boleta> boletas = boletaRepository.findAll();
        return ResponseEntity.ok(boletas);
    }
}
