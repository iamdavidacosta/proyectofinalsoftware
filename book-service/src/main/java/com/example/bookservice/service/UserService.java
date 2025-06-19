package com.example.bookservice.service;

import com.example.bookservice.model.Role;
import com.example.bookservice.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

@Service
public class UserService {
    @Value("${user.storage-path}")
    private String path;

    public List<User> findAll() throws IOException {
        Path file = Path.of(path);
        if (!Files.exists(file)) {
            return List.of();
        }
        return Files.readAllLines(file).stream()
                .map(line -> line.split(";"))
                .map(arr -> new User(arr[0], arr[1], Role.valueOf(arr[2])))
                .collect(Collectors.toList());
    }

    public User save(User user) throws IOException {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        Path file = Path.of(path);
        String record = String.join(";", id, user.getName(), user.getRole().name());
        Files.write(file, List.of(record),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        return user;
    }

    public User findById(String id) throws IOException {
        return findAll().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    }
}
