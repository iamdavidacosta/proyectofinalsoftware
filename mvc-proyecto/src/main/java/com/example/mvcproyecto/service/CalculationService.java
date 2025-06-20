package com.example.mvcproyecto.service;

import com.example.mvcproyecto.model.IntegrationRequest;
import com.example.mvcproyecto.model.MatrixRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class CalculationService {

    private final WebClient webClient;
    
    @Value("${calc.service.url}")
    private String baseUrl;

    public CalculationService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Map> multiplyMatrices(MatrixRequest request) {
        return webClient.post()
                .uri(baseUrl + "/matrix")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Map.class)
                .onErrorReturn(Map.of("error", "No se pudo realizar la multiplicación de matrices"));
    }

    public Mono<Map> integrateFunction(IntegrationRequest request) {
        return webClient.post()
                .uri(baseUrl + "/integrate")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Map.class)
                .onErrorReturn(Map.of("error", "No se pudo realizar la integración"));
    }
}
