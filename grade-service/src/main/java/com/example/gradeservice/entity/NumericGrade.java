package com.example.gradeservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("NUMERIC")
public class NumericGrade extends Grade {
    @Column(name = "numeric_value")
    private double numericValue;

    public double getNumericValue() { return numericValue; }
    public void setNumericValue(double numericValue) { this.numericValue = numericValue; }
}