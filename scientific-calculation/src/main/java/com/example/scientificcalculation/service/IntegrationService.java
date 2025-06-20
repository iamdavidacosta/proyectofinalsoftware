package com.example.scientificcalculation.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

@Service
public class IntegrationService {
    public double integrate(double lo, double hi, int sub, int threads) throws InterruptedException, ExecutionException {
        // Validaciones
        if (lo >= hi) {
            throw new IllegalArgumentException("El límite inferior debe ser menor que el superior");
        }
        if (sub <= 0) {
            throw new IllegalArgumentException("El número de subintervalos debe ser positivo");
        }
        if (threads <= 0) {
            throw new IllegalArgumentException("El número de threads debe ser positivo");
        }
        
        ExecutorService exec = Executors.newFixedThreadPool(threads);
        double h = (hi - lo) / sub;
        
        // Dividir el trabajo entre los threads
        int subintervalosPerThread = sub / threads;
        int remaining = sub % threads;
        
        List<Future<Double>> futures = new ArrayList<>();
        int currentStart = 0;
        
        for (int t = 0; t < threads; t++) {
            int subsForThisThread = subintervalosPerThread + (t < remaining ? 1 : 0);
            final int startIdx = currentStart;
            final int endIdx = currentStart + subsForThisThread;
            
            Callable<Double> task = () -> {
                double sum = 0;
                for (int i = startIdx; i < endIdx; i++) {
                    double x = lo + i * h;
                    sum += Math.sin(x);
                }
                return sum * h;
            };
            
            futures.add(exec.submit(task));
            currentStart = endIdx;
        }
        
        double total = 0;
        for (Future<Double> f : futures) {
            total += f.get();
        }
        
        exec.shutdown();
        return total;
    }
}
