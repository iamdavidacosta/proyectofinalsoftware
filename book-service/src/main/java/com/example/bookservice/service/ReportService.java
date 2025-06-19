package com.example.bookservice.service;

import com.example.bookservice.model.Book;
import com.example.bookservice.model.Loan;
import com.example.bookservice.model.Report;
import com.example.bookservice.model.User;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {
    private final UserService userService;
    private final BookService bookService;
    private final LoanService loanService;

    public ReportService(UserService us, BookService bs, LoanService ls) {
        this.userService = us;
        this.bookService = bs;
        this.loanService = ls;
    }

    public Report generate() throws IOException {
        List<User> users = userService.findAll();
        List<Book> books = bookService.findAll();
        List<Loan> loans = loanService.findAll();
        int totalFines = loans.stream().mapToInt(Loan::getFine).sum();
        return new Report(books, loans, users, totalFines);
    }
}