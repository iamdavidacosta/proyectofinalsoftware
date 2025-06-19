package com.example.mvcproyecto.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Map;

@Controller
public class LibraryMvcController {

    private final WebClient client;
    @Value("${library.service.url}") private String baseUrl;

    public LibraryMvcController(WebClient client) {
        this.client = client;
    }

    @GetMapping("/library")
    public String libraryPage() {
        return "library";
    }

    @PostMapping("/mvc/library/create")
    public String createBook(
            @RequestParam String title,
            Model model) {
        Mono<Map> created = client.post()
                .uri(baseUrl + "/books")
                .bodyValue(Map.of("title", title))
                .retrieve().bodyToMono(Map.class);
        model.addAttribute("bookCreateResult", created.block());
        return "fragments :: bookCreateResult";
    }

    @GetMapping("/mvc/library/list")
    public String listBooks(Model model) {
        Flux<Map> books = client.get()
                .uri(baseUrl + "/books")
                .retrieve().bodyToFlux(Map.class);
        model.addAttribute("booksList", books.collectList().block());
        return "fragments :: booksList";
    }
}
