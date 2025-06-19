package com.example.bookservice.controller;

import com.example.bookservice.model.Loan;
import com.example.bookservice.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanService service;
    public LoanController(LoanService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAll() throws IOException {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Loan> create(@RequestBody Loan loan) throws IOException {
        return ResponseEntity.ok(service.save(loan));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getById(@PathVariable String id) throws IOException {
        return ResponseEntity.ok(
                service.findAll().stream()
                        .filter(l -> l.getLoanId().equals(id))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Loan not found: " + id))
        );
    }
}
