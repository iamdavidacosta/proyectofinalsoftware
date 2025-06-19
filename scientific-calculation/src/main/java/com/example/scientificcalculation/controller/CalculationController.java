package com.example.scientificcalculation.controller;

import com.example.scientificcalculation.dto.IntegrationRequest;
import com.example.scientificcalculation.dto.IntegrationResponse;
import com.example.scientificcalculation.dto.MatrixRequest;
import com.example.scientificcalculation.dto.MatrixResponse;
import com.example.scientificcalculation.service.IntegrationService;
import com.example.scientificcalculation.service.MatrixService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/calc")
public class CalculationController {
    private final MatrixService matrixService;
    private final IntegrationService integrationService;

    public CalculationController(MatrixService ms, IntegrationService is) {
        this.matrixService = ms;
        this.integrationService = is;
    }

    @PostMapping("/matrix")
    public MatrixResponse multiply(@RequestBody MatrixRequest req) {
        long start = System.currentTimeMillis();
        int[][] res = matrixService.multiply(req.getA(), req.getB());
        long duration = System.currentTimeMillis() - start;
        MatrixResponse resp = new MatrixResponse();
        resp.setResult(res);
        resp.setDurationMs(duration);
        return resp;
    }

    @PostMapping("/integrate")
    public IntegrationResponse integrate(@RequestBody IntegrationRequest req) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        double val = integrationService.integrate(req.getLower(), req.getUpper(), req.getSubintervals(), req.getThreads());
        long duration = System.currentTimeMillis() - start;
        IntegrationResponse resp = new IntegrationResponse();
        resp.setValue(val);
        resp.setDurationMs(duration);
        resp.setError(Math.abs((req.getUpper() - req.getLower()) - val));
        return resp;
    }
}
