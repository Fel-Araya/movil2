package com.example.miapp.controller;

import com.example.miapp.model.*;
import com.example.miapp.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoItemRepository carritoItemRepository;

    @GetMapping("/{usuarioId}")
    public Carrito getCarrito(@PathVariable Long usuarioId) {
        return carritoRepository.findByUserId(usuarioId);
    }

    @PostMapping("/crear/{usuarioId}")
    public Carrito crearCarrito(@PathVariable Long usuarioId) {
        User usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario == null) return null;

        Carrito carrito = new Carrito(usuario);
        return carritoRepository.save(carrito);
    }

    @PostMapping("/{usuarioId}/agregar/{productoId}")
    public Carrito agregarProducto(@PathVariable Long usuarioId, @PathVariable Long productoId) {

        Carrito carrito = carritoRepository.findByUserId(usuarioId);
        if (carrito == null) return null;

        Producto producto = productoRepository.findById(productoId).orElse(null);
        if (producto == null) return null;

        for (CarritoItem item : carrito.getItems()) {
            if (item.getProducto().getId().equals(productoId)) {
                item.setCantidad(item.getCantidad() + 1);
                carritoItemRepository.save(item);
                return carritoRepository.save(carrito);
            }
        }

        CarritoItem nuevo = new CarritoItem(producto, 1);
        nuevo.setCarrito(carrito);

        carrito.getItems().add(nuevo);

        carritoItemRepository.save(nuevo);
        return carritoRepository.save(carrito);
    }

    @DeleteMapping("/item/{itemId}")
    public void eliminarItem(@PathVariable Long itemId) {
        carritoItemRepository.deleteById(itemId);
    }

    @DeleteMapping("/vaciar/{usuarioId}")
    public void vaciarCarrito(@PathVariable Long usuarioId) {
        Carrito carrito = carritoRepository.findByUserId(usuarioId);
        if (carrito != null) {
            carrito.getItems().clear();
            carritoRepository.save(carrito);
        }
    }
}
