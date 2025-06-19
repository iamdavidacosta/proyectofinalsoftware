package com.example.bookservice.service;

import com.example.bookservice.model.Loan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LoanService {
    @Value("${loan.storage-path}")
    private String path;

    private final UserService userService;
    private final BookService bookService;

    public LoanService(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    public List<Loan> findAll() throws IOException {
        Path file = Path.of(path);
        List<Loan> result = new ArrayList<>();
        if (!Files.exists(file)) {
            return result;
        }
        for (String line : Files.readAllLines(file)) {
            String[] arr = line.split(";");
            Loan l = new Loan();
            l.setLoanId(arr[0]);
            l.setUserId(arr[1]);
            l.setIsbn(arr[2]);
            l.setLoanDate(LocalDate.parse(arr[3]));
            l.setDueDate(LocalDate.parse(arr[4]));
            l.setReturnDate(arr[5].isEmpty() ? null : LocalDate.parse(arr[5]));
            if (l.getReturnDate() != null && l.getReturnDate().isAfter(l.getDueDate())) {
                long days = ChronoUnit.DAYS.between(l.getDueDate(), l.getReturnDate());
                l.setDaysOverdue(days);
                try {
                    int fine = (int) (days * userService.findById(l.getUserId()).getRole().getFinePerDay());
                    l.setFine(fine);
                } catch (IOException e) {
                    throw new UncheckedIOException("Error reading user for fine calculation", e);
                }
            }
            result.add(l);
        }
        return result;
    }

    public Loan save(Loan loan) throws IOException {
        String id = UUID.randomUUID().toString();
        loan.setLoanId(id);
        LocalDate due = loan.getLoanDate().plusDays(14);
        loan.setDueDate(due);
        Path file = Path.of(path);
        String record = String.join(";",
                id,
                loan.getUserId(),
                loan.getIsbn(),
                loan.getLoanDate().toString(),
                due.toString(),
                "",
                "0",
                "0"
        );
        Files.write(file, List.of(record),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        return loan;
    }
}
