package com.example.gradeservice.services;

import com.example.gradeservice.interfaces.IGradeService;
import com.example.gradeservice.entity.*;
import com.example.gradeservice.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class GradeServiceImpl implements IGradeService {
    private final StudentRepository studentRepo;
    private final CourseRepository  courseRepo;
    private final GradeRepository   gradeRepo;

    public GradeServiceImpl(StudentRepository s, CourseRepository c, GradeRepository g) {
        this.studentRepo = s;
        this.courseRepo  = c;
        this.gradeRepo   = g;
    }

    public List<Student> listStudents() { return studentRepo.findAll(); }
    public Student createStudent(Student s) { return studentRepo.save(s); }
    public List<Course> listCourses() { return courseRepo.findAll(); }
    public Course createCourse(Course c) { return courseRepo.save(c); }

    @Override
    public Grade assignNumeric(String studentId, String courseId, double value) {
        Student s = studentRepo.findById(studentId)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Student not found: " + studentId));
        Course c = courseRepo.findById(courseId)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Course not found: " + courseId));
        NumericGrade g = new NumericGrade();
        g.setStudent(s);
        g.setCourse(c);
        g.setDate(java.time.LocalDate.now());
        g.setNumericValue(value);
        return gradeRepo.save(g);
    }

    @Override
    public Grade assignQualitative(String studentId, String courseId, String value) {
        Student s = studentRepo.findById(studentId)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Student not found: " + studentId));
        Course c = courseRepo.findById(courseId)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Course not found: " + courseId));
        QualitativeGrade g = new QualitativeGrade();
        g.setStudent(s);
        g.setCourse(c);
        g.setDate(java.time.LocalDate.now());
        g.setQualitativeValue(value);
        return gradeRepo.save(g);
    }

    public List<Grade> gradesByCourse(String courseId) {
        return gradeRepo.findByCourseId(courseId);
    }

    @Override
    public List<NumericGrade> listNumericGrades() {
        return gradeRepo.findAllNumericGrades();
    }

    @Override
    public List<QualitativeGrade> listQualitativeGrades() {
        return gradeRepo.findAllQualitativeGrades();
    }
}