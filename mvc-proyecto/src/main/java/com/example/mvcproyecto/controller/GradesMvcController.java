package com.example.mvcproyecto.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Map;

@Controller
public class GradesMvcController {

    private final WebClient client;
    @Value("${grade.service.url}") private String baseUrl;

    public GradesMvcController(WebClient client) {
        this.client = client;
    }

    @GetMapping("/grades")
    public String gradesPage() {
        return "grades";
    }

    @PostMapping("/mvc/grades/create")
    public String createGrade(
            @RequestParam String studentId,
            @RequestParam String courseId,
            @RequestParam double value,
            @RequestParam String gradeType,
            Model model) {
        Mono<Map> created = client.post()
                .uri(baseUrl)
                .bodyValue(Map.of(
                        "studentId", studentId,
                        "courseId", courseId,
                        "value", value,
                        "gradeType", gradeType))
                .retrieve().bodyToMono(Map.class);
        model.addAttribute("gradeCreateResult", created.block());
        return "fragments :: gradeCreateResult";
    }

    @GetMapping("/mvc/grades/list")
    public String listGrades(Model model) {
        Flux<Map> grades = client.get()
                .uri(baseUrl)
                .retrieve().bodyToFlux(Map.class);
        model.addAttribute("gradesList", grades.collectList().block());
        return "fragments :: gradesList";
    }
}
