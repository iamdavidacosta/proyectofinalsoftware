package com.example.scientificcalculation.dto;

public class MatrixResponse {
    private int[][] result;
    private long durationMs;
    public int[][] getResult() { return result; }
    public void setResult(int[][] result) { this.result = result; }
    public long getDurationMs() { return durationMs; }
    public void setDurationMs(long durationMs) { this.durationMs = durationMs; }
}
