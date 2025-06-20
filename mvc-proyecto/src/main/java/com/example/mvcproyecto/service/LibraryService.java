package com.example.mvcproyecto.service;

import com.example.mvcproyecto.model.Book;
import com.example.mvcproyecto.model.Loan;
import com.example.mvcproyecto.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class LibraryService {

    private final WebClient webClient;
    
    @Value("${library.service.url}")
    private String baseUrl;

    public LibraryService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Operaciones de libros
    public Mono<List<Book>> getAllBooks() {
        return webClient.get()
                .uri(baseUrl + "/books")
                .retrieve()
                .bodyToFlux(Book.class)
                .collectList()
                .onErrorReturn(List.of());
    }

    public Mono<Book> getBookByIsbn(String isbn) {
        return webClient.get()
                .uri(baseUrl + "/books/" + isbn)
                .retrieve()
                .bodyToMono(Book.class)
                .onErrorReturn(new Book());
    }

    public Mono<Book> createBook(Book book) {
        return webClient.post()
                .uri(baseUrl + "/books")
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Book.class)
                .onErrorReturn(new Book());
    }

    // Operaciones de usuarios
    public Mono<List<User>> getAllUsers() {
        return webClient.get()
                .uri(baseUrl + "/users")
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .onErrorReturn(List.of());
    }

    public Mono<User> getUserById(String userId) {
        return webClient.get()
                .uri(baseUrl + "/users/" + userId)
                .retrieve()
                .bodyToMono(User.class)
                .onErrorReturn(new User());
    }

    public Mono<User> createUser(User user) {
        return webClient.post()
                .uri(baseUrl + "/users")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .onErrorReturn(new User());
    }

    // Operaciones de préstamos
    public Mono<List<Loan>> getAllLoans() {
        return webClient.get()
                .uri(baseUrl + "/loans")
                .retrieve()
                .bodyToFlux(Loan.class)
                .collectList()
                .onErrorReturn(List.of());
    }

    public Mono<Loan> getLoanById(String loanId) {
        return webClient.get()
                .uri(baseUrl + "/loans/" + loanId)
                .retrieve()
                .bodyToMono(Loan.class)
                .onErrorReturn(new Loan());
    }

    public Mono<Loan> createLoan(Loan loan) {
        return webClient.post()
                .uri(baseUrl + "/loans")
                .bodyValue(loan)
                .retrieve()
                .bodyToMono(Loan.class)
                .onErrorReturn(new Loan());
    }    // Reporte
    public Mono<Map> getReport() {
        return webClient.get()
                .uri(baseUrl + "/report")
                .retrieve()
                .bodyToMono(Map.class)
                .onErrorReturn(Map.of("error", "No se pudo obtener el reporte"));
    }
}
