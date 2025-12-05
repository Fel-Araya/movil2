package com.example.miapp.controller;

import com.example.miapp.model.Boleta;
import com.example.miapp.model.BoletaRequest;
import com.example.miapp.model.Producto;
import com.example.miapp.repository.BoletaRepository;
import com.example.miapp.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // POST exacto en /api/boletas
    @PostMapping
    public ResponseEntity<Boleta> crearBoleta(@RequestBody BoletaRequest request) {

        // Convertir lista de ProductoId a lista de Long para buscar productos reales
        List<Long> productoIds = request.getProductos()
                                         .stream()
                                         .map(p -> p.getId())
                                         .collect(Collectors.toList());

        List<Producto> productos = productoRepository.findAllById(productoIds);

        // Validación: si no encuentra algún producto, devolver 400
        if (productos.size() != productoIds.size()) {
            return ResponseEntity.badRequest().build();
        }

        // Parsear fecha del request o usar la actual
        Date fecha = new Date();
        if (request.getFecha() != null && !request.getFecha().isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                fecha = sdf.parse(request.getFecha());
            } catch (Exception e) {
                fecha = new Date(); // fallback
            }
        }

        // Crear boleta
        Boleta boleta = new Boleta();
        boleta.setProductos(productos);
        boleta.setTotal(request.getTotal());
        boleta.setFecha(fecha);

        // Guardar en DB
        boletaRepository.save(boleta);

        return ResponseEntity.ok(boleta);
    }
}
