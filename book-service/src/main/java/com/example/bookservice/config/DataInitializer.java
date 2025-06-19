package com.example.bookservice.config;

import com.example.bookservice.model.Book;
import com.example.bookservice.model.Loan;
import com.example.bookservice.model.Role;
import com.example.bookservice.model.User;
import com.example.bookservice.service.BookService;
import com.example.bookservice.service.LoanService;
import com.example.bookservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner run(UserService us, BookService bs, LoanService ls) {
        return args -> {
            Path dataDir = Path.of("data");
            if (!Files.exists(dataDir)) {
                Files.createDirectories(dataDir);
            }

            if (Files.list(dataDir).findAny().isEmpty()) {
                // Usuarios de demo
                us.save(new User(null, "Juan Pérez", Role.ESTUDIANTE));
                us.save(new User(null, "María López", Role.DOCENTE));
                us.save(new User(null, "Carlos Ruiz", Role.EMPLEADO));

                // Libros de demo
                bs.save(new Book("9788425346475", "Don Quijote de la Mancha"));
                bs.save(new Book("9788499890944", "La sombra del viento"));
                bs.save(new Book("9788466323466", "Cien años de soledad"));

                // Préstamo de demo: toma el primer usuario y el primer libro
                String userId = us.findAll().get(0).getId();
                String isbn   = bs.findAll().get(0).getIsbn();
                Loan demoLoan = new Loan();
                demoLoan.setUserId(userId);
                demoLoan.setIsbn(isbn);
                demoLoan.setLoanDate(LocalDate.now());
                ls.save(demoLoan);
            }
        };
    }
}
