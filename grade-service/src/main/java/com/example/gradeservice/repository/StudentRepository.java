package com.example.gradeservice.repository;

import com.example.gradeservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {}