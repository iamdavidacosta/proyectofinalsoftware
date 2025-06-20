package com.example.mvcproyecto.controller;

import com.example.mvcproyecto.model.Course;
import com.example.mvcproyecto.model.Student;
import com.example.mvcproyecto.service.GradeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/grades")
public class GradesMvcController {

    private final GradeService gradeService;

    public GradesMvcController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public String gradesPage(Model model) {
        // Cargar datos iniciales para la página
        List<Student> students = gradeService.getAllStudents().block();
        List<Course> courses = gradeService.getAllCourses().block();
        
        model.addAttribute("students", students);
        model.addAttribute("courses", courses);
        return "grades";
    }

    // === OPERACIONES DE ESTUDIANTES ===
    
    @PostMapping("/students/create")
    public String createStudent(@RequestParam String id, @RequestParam String name, Model model) {
        Student student = new Student(id, name);
        Student created = gradeService.createStudent(student).block();
        model.addAttribute("studentCreateResult", created);
        return "fragments :: studentCreateResult";
    }

    @GetMapping("/students/list")
    public String listStudents(Model model) {
        List<Student> students = gradeService.getAllStudents().block();
        model.addAttribute("studentsList", students);
        return "fragments :: studentsList";
    }

    // === OPERACIONES DE CURSOS ===
    
    @PostMapping("/courses/create")
    public String createCourse(@RequestParam String id, @RequestParam String name, Model model) {
        Course course = new Course(id, name);
        Course created = gradeService.createCourse(course).block();
        model.addAttribute("courseCreateResult", created);
        return "fragments :: courseCreateResult";
    }

    @GetMapping("/courses/list")
    public String listCourses(Model model) {
        List<Course> courses = gradeService.getAllCourses().block();
        model.addAttribute("coursesList", courses);
        return "fragments :: coursesList";
    }    // === OPERACIONES DE CALIFICACIONES ===
    
    @PostMapping("/assign/numeric")
    public String assignNumericGrade(
            @RequestParam String studentId,
            @RequestParam String courseId,
            @RequestParam double value,
            Model model) {
        
        Map<String, Object> result = gradeService.assignNumericGrade(studentId, courseId, value).block();
        model.addAttribute("gradeResult", result);
        return "fragments :: gradeResult";
    }

    @PostMapping("/assign/qualitative")
    public String assignQualitativeGrade(
            @RequestParam String studentId,
            @RequestParam String courseId,
            @RequestParam String value,
            Model model) {
        
        Map<String, Object> result = gradeService.assignQualitativeGrade(studentId, courseId, value).block();
        model.addAttribute("gradeResult", result);
        return "fragments :: gradeResult";
    }    // === ENDPOINTS PARA LISTAR CALIFICACIONES ===
    
    @GetMapping("/numeric/list")
    public String listNumericGrades(Model model) {
        List<Map<String, Object>> numericGrades = gradeService.getAllNumericGrades().block();
        model.addAttribute("numericGradesList", numericGrades);
        return "fragments :: numericGradesList";
    }

    @GetMapping("/qualitative/list")
    public String listQualitativeGrades(Model model) {
        List<Map<String, Object>> qualitativeGrades = gradeService.getAllQualitativeGrades().block();
        model.addAttribute("qualitativeGradesList", qualitativeGrades);
        return "fragments :: qualitativeGradesList";
    }

    // === ENDPOINTS AJAX PARA SELECTORES ===
    
    @GetMapping("/students/options")
    @ResponseBody
    public List<Student> getStudentsForSelect() {
        return gradeService.getAllStudents().block();
    }

    @GetMapping("/courses/options")
    @ResponseBody  
    public List<Course> getCoursesForSelect() {
        return gradeService.getAllCourses().block();
    }
}
