package com.example.mvcproyecto.controller;

import com.example.mvcproyecto.model.Book;
import com.example.mvcproyecto.model.Loan;
import com.example.mvcproyecto.model.User;
import com.example.mvcproyecto.service.LibraryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/library")
public class LibraryMvcController {

    private final LibraryService libraryService;

    public LibraryMvcController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public String libraryPage(Model model) {
        // Cargar datos iniciales para la página
        List<Book> books = libraryService.getAllBooks().block();
        List<User> users = libraryService.getAllUsers().block();
        
        model.addAttribute("books", books);
        model.addAttribute("users", users);
        return "library";
    }

    // === OPERACIONES DE LIBROS ===
    
    @PostMapping("/books/create")
    public String createBook(@RequestParam String title, Model model) {
        Book book = new Book(title);
        Book created = libraryService.createBook(book).block();
        model.addAttribute("bookCreateResult", created);
        return "fragments :: bookCreateResult";
    }

    @GetMapping("/books/list")
    public String listBooks(Model model) {
        List<Book> books = libraryService.getAllBooks().block();
        model.addAttribute("booksList", books);
        return "fragments :: booksList";
    }

    @GetMapping("/books/{isbn}")
    public String getBook(@PathVariable String isbn, Model model) {
        Book book = libraryService.getBookByIsbn(isbn).block();
        model.addAttribute("book", book);
        return "fragments :: bookDetail";
    }

    // === OPERACIONES DE USUARIOS ===
    
    @PostMapping("/users/create")
    public String createUser(@RequestParam String name, @RequestParam String role, Model model) {
        User user = new User(name, role);
        User created = libraryService.createUser(user).block();
        model.addAttribute("userCreateResult", created);
        return "fragments :: userCreateResult";
    }

    @GetMapping("/users/list")
    public String listUsers(Model model) {
        List<User> users = libraryService.getAllUsers().block();
        model.addAttribute("usersList", users);
        return "fragments :: usersList";
    }

    @GetMapping("/users/{userId}")
    public String getUser(@PathVariable String userId, Model model) {
        User user = libraryService.getUserById(userId).block();
        model.addAttribute("user", user);
        return "fragments :: userDetail";
    }

    // === OPERACIONES DE PRÉSTAMOS ===
    
    @PostMapping("/loans/create")
    public String createLoan(
            @RequestParam String userId,
            @RequestParam String isbn,
            @RequestParam String loanDate,
            Model model) {
        
        Loan loan = new Loan(userId, isbn, LocalDate.parse(loanDate));
        Loan created = libraryService.createLoan(loan).block();
        model.addAttribute("loanCreateResult", created);
        return "fragments :: loanCreateResult";
    }    @GetMapping("/loans/list")
    public String listLoans(Model model) {
        List<Loan> loans = libraryService.getAllLoans().block();
        List<Book> books = libraryService.getAllBooks().block();
        List<User> users = libraryService.getAllUsers().block();
        
        model.addAttribute("loansList", loans);
        model.addAttribute("booksList", books);
        model.addAttribute("usersList", users);
        return "fragments :: loansList";
    }

    @GetMapping("/loans/{loanId}")
    public String getLoan(@PathVariable String loanId, Model model) {
        Loan loan = libraryService.getLoanById(loanId).block();
        model.addAttribute("loan", loan);
        return "fragments :: loanDetail";
    }

    // === ENDPOINTS AJAX PARA SELECTORES ===
    
    @GetMapping("/users/options")
    @ResponseBody
    public List<User> getUsersForSelect() {
        return libraryService.getAllUsers().block();
    }

    @GetMapping("/books/options")
    @ResponseBody  
    public List<Book> getBooksForSelect() {
        return libraryService.getAllBooks().block();
    }    // === REPORTE ===
    
    @GetMapping("/report")
    @ResponseBody
    public Map<String, Object> getReport() {
        return libraryService.getReport().block();
    }

    // === DATOS PARA ESTADÍSTICAS ===

    @GetMapping("/loans/data")
    @ResponseBody
    public List<Loan> getLoansData() {
        return libraryService.getAllLoans().block();
    }
}
