package com.example.mvcproyecto.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    public String handleWebClientException(WebClientResponseException ex, Model model) {
        model.addAttribute("error", "Error de conexión con el servicio: " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("error", "Error interno: " + ex.getMessage());
        return "error";
    }
}
