package com.example.gradeservice.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "GRADE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "grade_type")
public abstract class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    private LocalDate date;

    public Long getId() { return id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}