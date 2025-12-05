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

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping("/boletas")
    public ResponseEntity<Boleta> crearBoleta(@RequestBody BoletaRequest request) {
        // Obtener productos reales por ID
        List<Producto> productos = productoRepository.findAllById(request.getProductos());

        // Parsear fecha si quieres usar la del request, o usar fecha actual
        Date fecha = new Date();
        if (request.getFecha() != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                fecha = sdf.parse(request.getFecha());
            } catch (Exception e) {
                fecha = new Date(); // fallback
            }
        }

        Boleta boleta = new Boleta();
        boleta.setProductos(productos);
        boleta.setTotal(request.getTotal());
        boleta.setFecha(fecha);

        boletaRepository.save(boleta);

        return ResponseEntity.ok(boleta);
    }
}
