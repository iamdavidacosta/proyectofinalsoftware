package com.example.scientificcalculation.service;
import org.springframework.stereotype.Service;

@Service
public class MatrixService {
    public int[][] multiply(int[][] a, int[][] b) {
        int m = a.length;
        int n = b.length;
        int p = b[0].length;
        int[][] c = new int[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }
}
