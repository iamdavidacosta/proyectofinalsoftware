package com.example.bookservice.model;

import java.time.LocalDate;

public class Loan {
    private String loanId;
    private String userId;
    private String isbn;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private long daysOverdue;
    private int fine;

    public Loan() {}

    public Loan(String loanId, String userId, String isbn,
                LocalDate loanDate, LocalDate dueDate,
                LocalDate returnDate, long daysOverdue, int fine) {
        this.loanId = loanId;
        this.userId = userId;
        this.isbn = isbn;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.daysOverdue = daysOverdue;
        this.fine = fine;
    }


    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public long getDaysOverdue() {
        return daysOverdue;
    }

    public void setDaysOverdue(long daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }
}
