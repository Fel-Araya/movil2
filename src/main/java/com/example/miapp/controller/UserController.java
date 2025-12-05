package com.example.miapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.miapp.model.User;
import com.example.miapp.repository.*;;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;


    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    public User create(@RequestBody User usuario) {
        return userRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User usuario) {
        User u = userRepository.findById(id).orElse(null);
        if (u == null) return null;

        u.setNombre(usuario.getNombre());
        u.setEmail(usuario.getEmail());
        return userRepository.save(u);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}