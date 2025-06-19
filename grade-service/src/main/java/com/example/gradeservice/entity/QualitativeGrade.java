package com.example.gradeservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("QUALITATIVE")
public class QualitativeGrade extends Grade {
    @Column(name = "qualitative_value")
    private String qualitativeValue; // APROBO, REPROBO, PENDIENTE

    public String getQualitativeValue() { return qualitativeValue; }
    public void setQualitativeValue(String qualitativeValue) { this.qualitativeValue = qualitativeValue; }
}