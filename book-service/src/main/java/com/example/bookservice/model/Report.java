package com.example.bookservice.model;

import java.util.List;

public class Report {
    private List<Book> books;
    private List<Loan> activeLoans;
    private List<User> users;
    private int totalFines;

    public Report() {}

    public Report(List<Book> books, List<Loan> activeLoans, List<User> users, int totalFines) {
        this.books = books;
        this.activeLoans = activeLoans;
        this.users = users;
        this.totalFines = totalFines;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Loan> getActiveLoans() {
        return activeLoans;
    }

    public void setActiveLoans(List<Loan> activeLoans) {
        this.activeLoans = activeLoans;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getTotalFines() {
        return totalFines;
    }

    public void setTotalFines(int totalFines) {
        this.totalFines = totalFines;
    }
}
