package com.example.gradeservice.repository;

import com.example.gradeservice.entity.Grade;
import com.example.gradeservice.entity.NumericGrade;
import com.example.gradeservice.entity.QualitativeGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByCourseId(String courseId);
    
    @Query("SELECT g FROM NumericGrade g")
    List<NumericGrade> findAllNumericGrades();
    
    @Query("SELECT g FROM QualitativeGrade g")
    List<QualitativeGrade> findAllQualitativeGrades();
}