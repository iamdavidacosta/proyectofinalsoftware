package com.example.gradeservice.repository;

import com.example.gradeservice.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByCourseId(String courseId);
}