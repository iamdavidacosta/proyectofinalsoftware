package com.example.scientificcalculation.dto;

public class IntegrationResponse {
    private double value;
    private long durationMs;
    private double error;
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public long getDurationMs() { return durationMs; }
    public void setDurationMs(long durationMs) { this.durationMs = durationMs; }
    public double getError() { return error; }
    public void setError(double error) { this.error = error; }
}
