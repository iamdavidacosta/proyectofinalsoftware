package com.example.mvcproyecto.controller;

import com.example.mvcproyecto.model.IntegrationRequest;
import com.example.mvcproyecto.model.MatrixRequest;
import com.example.mvcproyecto.service.CalculationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/calc")
public class CalculationMvcController {

    private final CalculationService calculationService;
    private final ObjectMapper mapper;

    public CalculationMvcController(CalculationService calculationService, ObjectMapper mapper) {
        this.calculationService = calculationService;
        this.mapper = mapper;
    }

    @GetMapping
    public String calcPage() {
        return "calc";
    }

    @PostMapping("/matrix")
    public String multiplyMatrices(
            @RequestParam String matrixA,
            @RequestParam String matrixB,
            Model model) {
        
        try {
            int[][] aArr = mapper.readValue(matrixA, int[][].class);
            int[][] bArr = mapper.readValue(matrixB, int[][].class);
            
            MatrixRequest request = new MatrixRequest(aArr, bArr);
            Map<String, Object> result = calculationService.multiplyMatrices(request).block();
            
            model.addAttribute("matrixResult", result);
        } catch (Exception e) {
            model.addAttribute("matrixResult", Map.of("error", "Error al procesar las matrices: " + e.getMessage()));
        }
        
        return "fragments :: matrixResult";
    }

    @PostMapping("/integrate")
    public String integrateFunction(
            @RequestParam double lower,
            @RequestParam double upper,
            @RequestParam int subintervals,
            @RequestParam int threads,
            Model model) {
        
        IntegrationRequest request = new IntegrationRequest(lower, upper, subintervals, threads);
        Map<String, Object> result = calculationService.integrateFunction(request).block();
        
        model.addAttribute("integrateResult", result);
        return "fragments :: integrateResult";
    }

    // Endpoints JSON para el frontend dinámico
    
    @PostMapping("/matrix/json")
    @ResponseBody
    public Map<String, Object> multiplyMatricesJson(@RequestBody Map<String, Object> requestData) {
        try {
            Object aObj = requestData.get("a");
            Object bObj = requestData.get("b");
            
            int[][] aArr = mapper.convertValue(aObj, int[][].class);
            int[][] bArr = mapper.convertValue(bObj, int[][].class);
            
            MatrixRequest request = new MatrixRequest(aArr, bArr);
            return calculationService.multiplyMatrices(request).block();
        } catch (Exception e) {
            return Map.of("error", "Error al procesar las matrices: " + e.getMessage());
        }
    }

    @PostMapping("/integrate/json")
    @ResponseBody
    public Map<String, Object> integrateFunctionJson(@RequestBody IntegrationRequest request) {
        try {
            return calculationService.integrateFunction(request).block();
        } catch (Exception e) {
            return Map.of("error", "Error al calcular la integración: " + e.getMessage());
        }
    }
}