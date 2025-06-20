package com.example.gradeservice.interfaces;

import com.example.gradeservice.entity.*;
import java.util.List;

public interface IGradeService {
    List<Student> listStudents();
    Student createStudent(Student s);
    List<Course> listCourses();
    Course createCourse(Course c);
    Grade assignNumeric(String studentId, String courseId, double value);
    Grade assignQualitative(String studentId, String courseId, String value);
    List<Grade> gradesByCourse(String courseId);
    List<NumericGrade> listNumericGrades();
    List<QualitativeGrade> listQualitativeGrades();
}
