package com.example.bookservice.controller;

import com.example.bookservice.model.Report;
import com.example.bookservice.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService service;
    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Report> getReport() throws IOException {
        return ResponseEntity.ok(service.generate());
    }
}
