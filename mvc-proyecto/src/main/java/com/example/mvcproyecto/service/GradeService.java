package com.example.mvcproyecto.service;

import com.example.mvcproyecto.model.Course;
import com.example.mvcproyecto.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GradeService {

    private final WebClient webClient;
    
    @Value("${grade.service.url}")
    private String baseUrl;

    public GradeService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Operaciones de estudiantes
    public Mono<List<Student>> getAllStudents() {
        return webClient.get()
                .uri(baseUrl + "/students")
                .retrieve()
                .bodyToFlux(Student.class)
                .collectList()
                .onErrorReturn(List.of());
    }

    public Mono<Student> createStudent(Student student) {
        return webClient.post()
                .uri(baseUrl + "/students")
                .bodyValue(student)
                .retrieve()
                .bodyToMono(Student.class)
                .onErrorReturn(new Student());
    }

    // Operaciones de cursos
    public Mono<List<Course>> getAllCourses() {
        return webClient.get()
                .uri(baseUrl + "/courses")
                .retrieve()
                .bodyToFlux(Course.class)
                .collectList()
                .onErrorReturn(List.of());
    }

    public Mono<Course> createCourse(Course course) {
        return webClient.post()
                .uri(baseUrl + "/courses")
                .bodyValue(course)
                .retrieve()
                .bodyToMono(Course.class)
                .onErrorReturn(new Course());
    }    // Operaciones de calificaciones
    public Mono<Map<String, Object>> assignNumericGrade(String studentId, String courseId, double value) {
        String url = baseUrl + "/grades/numeric";
        System.out.println("Calling numeric grade endpoint: " + url);
        System.out.println("Parameters: studentId=" + studentId + ", courseId=" + courseId + ", value=" + value);        return webClient.post()
                .uri(url + "?studentId=" + studentId + "&courseId=" + courseId + "&value=" + value)
                .retrieve()
                .bodyToMono(Map.class)
                .map(result -> (Map<String, Object>) result)
                .doOnSuccess(result -> System.out.println("Numeric grade success: " + result))
                .doOnError(error -> System.err.println("Numeric grade error: " + error.getMessage()))
                .onErrorReturn(Map.of("error", "No se pudo asignar la calificación numérica"));
    }

    public Mono<Map<String, Object>> assignQualitativeGrade(String studentId, String courseId, String value) {
        String url = baseUrl + "/grades/qualitative";
        System.out.println("Calling qualitative grade endpoint: " + url);
        System.out.println("Parameters: studentId=" + studentId + ", courseId=" + courseId + ", value=" + value);        return webClient.post()
                .uri(url + "?studentId=" + studentId + "&courseId=" + courseId + "&value=" + value)
                .retrieve()
                .bodyToMono(Map.class)
                .map(result -> (Map<String, Object>) result)
                .doOnSuccess(result -> System.out.println("Qualitative grade success: " + result))
                .doOnError(error -> System.err.println("Qualitative grade error: " + error.getMessage()))
                .onErrorReturn(Map.of("error", "No se pudo asignar la calificación cualitativa"));
    }
    
    // Operaciones para listar calificaciones
    public Mono<List<Map<String, Object>>> getAllNumericGrades() {
        return webClient.get()
                .uri(baseUrl + "/grades/numeric")
                .retrieve()
                .bodyToFlux(Map.class)
                .map(result -> (Map<String, Object>) result)
                .collectList()
                .onErrorReturn(List.of());
    }

    public Mono<List<Map<String, Object>>> getAllQualitativeGrades() {
        return webClient.get()
                .uri(baseUrl + "/grades/qualitative")
                .retrieve()
                .bodyToFlux(Map.class)
                .map(result -> (Map<String, Object>) result)
                .collectList()
                .onErrorReturn(List.of());
    }
}
