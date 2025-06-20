package com.example.gradeservice.controller;

import com.example.gradeservice.entity.*;
import com.example.gradeservice.interfaces.IGradeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GradeController {
    private final IGradeService service;
    public GradeController(IGradeService service) { this.service = service; }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return service.listStudents();
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student s) {
        return service.createStudent(s);
    }

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return service.listCourses();
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course c) {
        return service.createCourse(c);
    }

    @PostMapping("/grades/numeric")
    public Grade addNumeric(@RequestParam String studentId, @RequestParam String courseId, @RequestParam double value) {
        return service.assignNumeric(studentId, courseId, value);
    }

    @PostMapping("/grades/qualitative")
    public Grade addQualitative(@RequestParam String studentId, @RequestParam String courseId, @RequestParam String value) {
        return service.assignQualitative(studentId, courseId, value);
    }

    @GetMapping("/grades/course/{id}")
    public List<Grade> getByCourse(@PathVariable String id) {
        return service.gradesByCourse(id);
    }

    @GetMapping("/grades/numeric")
    public List<NumericGrade> getNumericGrades() {
        return service.listNumericGrades();
    }

    @GetMapping("/grades/qualitative")
    public List<QualitativeGrade> getQualitativeGrades() {
        return service.listQualitativeGrades();
    }
}