package com.example.scientificcalculation.dto;

public class IntegrationRequest {
    private double lower;
    private double upper;
    private int subintervals;
    private int threads;
    public double getLower() { return lower; }
    public void setLower(double lower) { this.lower = lower; }
    public double getUpper() { return upper; }
    public void setUpper(double upper) { this.upper = upper; }
    public int getSubintervals() { return subintervals; }
    public void setSubintervals(int subintervals) { this.subintervals = subintervals; }
    public int getThreads() { return threads; }
    public void setThreads(int threads) { this.threads = threads; }
}