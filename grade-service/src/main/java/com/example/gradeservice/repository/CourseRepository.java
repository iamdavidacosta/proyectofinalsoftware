package com.example.gradeservice.repository;

import com.example.gradeservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {}