package com.example.scientificcalculation.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.*;

@Service
public class IntegrationService {
    public double integrate(double lo, double hi, int sub, int threads) throws InterruptedException, ExecutionException {
        ExecutorService exec = Executors.newFixedThreadPool(threads);
        double h = (hi - lo) / sub;
        Callable<Double> task = () -> {
            double sum = 0;
            for (int i = 0; i < sub; i++) {
                double x = lo + i * h;
                sum += Math.sin(x);
            }
            return sum * h;
        };
        Future<Double>[] futures = new Future[threads];
        for (int t = 0; t < threads; t++) {
            futures[t] = exec.submit(task);
        }
        double total = 0;
        for (Future<Double> f : futures) {
            total += f.get();
        }
        exec.shutdown();
        return total;
    }
}
