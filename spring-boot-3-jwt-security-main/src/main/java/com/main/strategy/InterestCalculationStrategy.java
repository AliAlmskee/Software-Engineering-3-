package com.main.strategy;

public interface InterestCalculationStrategy {
    double calculateInterest(double principal, double rate, int durationInMonths);
}
