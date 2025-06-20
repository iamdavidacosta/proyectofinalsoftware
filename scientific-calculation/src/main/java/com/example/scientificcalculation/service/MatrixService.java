package com.example.scientificcalculation.service;
import org.springframework.stereotype.Service;

@Service
public class MatrixService {
    public int[][] multiply(int[][] a, int[][] b) {
        // Validaciones
        if (a == null || b == null) {
            throw new IllegalArgumentException("Las matrices no pueden ser nulas");
        }
        if (a.length == 0 || b.length == 0) {
            throw new IllegalArgumentException("Las matrices no pueden estar vacías");
        }
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("El número de columnas de la matriz A debe ser igual al número de filas de la matriz B");
        }
        
        int m = a.length;           // filas de A
        int n = a[0].length;        // columnas de A = filas de B
        int p = b[0].length;        // columnas de B
        
        int[][] c = new int[m][p];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                c[i][j] = 0; // Inicializar explícitamente
                for (int k = 0; k < n; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }
}
