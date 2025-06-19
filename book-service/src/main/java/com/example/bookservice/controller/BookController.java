package com.example.bookservice.controller;

import com.example.bookservice.model.Book;
import com.example.bookservice.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() throws IOException {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) throws IOException {
        return ResponseEntity.ok(service.save(book));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getByIsbn(@PathVariable String isbn) throws IOException {
        return ResponseEntity.ok(service.findByIsbn(isbn));
    }
}