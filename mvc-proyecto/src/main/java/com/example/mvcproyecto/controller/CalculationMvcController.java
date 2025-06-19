package com.example.mvcproyecto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Map;

@Controller
public class CalculationMvcController {

    private final WebClient client;
    private final ObjectMapper mapper;

    @Value("${calc.service.url}")
    private String baseUrl;

    public CalculationMvcController(WebClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @GetMapping("/calc")
    public String calcPage() {
        return "calc";
    }

    @PostMapping("/mvc/calc/matrix")
    public String multiply(
            @RequestParam String a,
            @RequestParam String b,
            Model model) throws Exception {
        int[][] aArr = mapper.readValue(a, int[][].class);
        int[][] bArr = mapper.readValue(b, int[][].class);
        Mono<Map> response = client.post()
                .uri(baseUrl + "/matrix")
                .bodyValue(Map.of("a", aArr, "b", bArr))
                .retrieve()
                .bodyToMono(Map.class);
        Map res = response.block();
        model.addAttribute("matrixResult", res);
        return "fragments :: matrixResult";
    }

    @PostMapping("/mvc/calc/integrate")
    public String integrate(
            @RequestParam double lower,
            @RequestParam double upper,
            @RequestParam int subintervals,
            @RequestParam int threads,
            Model model) {
        Mono<Map> response = client.post()
                .uri(baseUrl + "/integrate")
                .bodyValue(Map.of(
                        "lower", lower,
                        "upper", upper,
                        "subintervals", subintervals,
                        "threads", threads))
                .retrieve()
                .bodyToMono(Map.class);
        Map res = response.block();
        model.addAttribute("integrateResult", res);
        return "fragments :: integrateResult";
    }
}