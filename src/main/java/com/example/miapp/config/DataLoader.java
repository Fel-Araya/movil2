package com.example.miapp.config;


import com.example.miapp.model.Producto;
import com.example.miapp.model.User;
import com.example.miapp.repository.ProductoRepository;
import com.example.miapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductoRepository productoRepository;

    public DataLoader(UserRepository userRepository, ProductoRepository productoRepository) {
        this.userRepository = userRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() > 0 || productoRepository.count() > 0) {
            return;
        }

        User user = new User();
        user.setNombre("Felipe");
        user.setEmail("Felipe@gmail.com");
        user.setPassword("123456");
        userRepository.save(user);

        Producto p1 = new Producto();
        p1.setNombre("Pro Deck");
        p1.setDescripcion("Tabla profesional de 8 capas");
        p1.setPrecio(89000.0);
        p1.setImagenUrl("https://raw.githubusercontent.com/Fel-Araya/app-movil/main/deck.webp");

        Producto p2 = new Producto();
        p2.setNombre("Speed Wheels");
        p1.setDescripcion("Ruedas de alta velocidad 52mm");
        p2.setPrecio(45000.0);
        p2.setImagenUrl("https://raw.githubusercontent.com/Fel-Araya/app-movil/main/speedwheels.jpg");

        Producto p3 = new Producto();
        p3.setNombre("Metal Trucks");
        p3.setDescripcion("Ejes de aluminio reforzado");
        p3.setPrecio(65000.0);
        p3.setImagenUrl("https://raw.githubusercontent.com/Fel-Araya/app-movil/main/metal_trucks.webp");

        productoRepository.save(p1);
        productoRepository.save(p2);
        productoRepository.save(p3);

        System.out.println("Datos iniciales cargados: 1 usuario y 3 productos.");
    }
}
