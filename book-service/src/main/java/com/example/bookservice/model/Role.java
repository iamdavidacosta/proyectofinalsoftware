package com.example.bookservice.model;

public enum Role {
    DOCENTE(5000),
    ESTUDIANTE(1000),
    EMPLEADO(2500);

    private final int finePerDay;
    Role(int finePerDay) { this.finePerDay = finePerDay; }
    public int getFinePerDay() { return finePerDay; }
}
