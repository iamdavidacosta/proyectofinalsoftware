package com.example.bookservice.service;

import com.example.bookservice.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Value("${book.storage-path}")
    private String path;

    public List<Book> findAll() throws IOException {
        Path file = Path.of(path);
        if (!Files.exists(file)) {
            return List.of();
        }
        return Files.readAllLines(file).stream()
                .map(line -> line.split(";"))
                .map(arr -> new Book(arr[0], arr[1]))
                .collect(Collectors.toList());
    }

    public Book save(Book book) throws IOException {
        String isbn = book.getIsbn() != null ? book.getIsbn() : UUID.randomUUID().toString();
        book.setIsbn(isbn);
        Path file = Path.of(path);
        String record = String.join(";", isbn, book.getTitle());
        Files.write(file, List.of(record),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        return book;
    }

    public Book findByIsbn(String isbn) throws IOException {
        return findAll().stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + isbn));
    }
}
