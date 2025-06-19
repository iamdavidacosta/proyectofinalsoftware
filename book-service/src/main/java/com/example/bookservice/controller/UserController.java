package com.example.bookservice.controller;

import com.example.bookservice.model.User;
import com.example.bookservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() throws IOException {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) throws IOException {
        return ResponseEntity.ok(service.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) throws IOException {
        return ResponseEntity.ok(service.findById(id));
    }
}
