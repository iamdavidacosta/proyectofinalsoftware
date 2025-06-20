package com.example.mvcproyecto.model;

import java.time.LocalDate;

public class Loan {
    private String loanId;
    private String userId;
    private String isbn;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private int daysOverdue;
    private double fine;
    private String status;

    public Loan() {}

    public Loan(String userId, String isbn, LocalDate loanDate) {
        this.userId = userId;
        this.isbn = isbn;
        this.loanDate = loanDate;
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
        this.dueDate = dueDate;    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }

    public void setDaysOverdue(int daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId='" + loanId + '\'' +
                ", userId='" + userId + '\'' +
                ", isbn='" + isbn + '\'' +
                ", loanDate=" + loanDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", daysOverdue=" + daysOverdue +
                ", fine=" + fine +
                ", status='" + status + '\'' +
                '}';
    }
}
