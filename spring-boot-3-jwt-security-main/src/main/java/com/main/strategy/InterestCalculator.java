package com.main.strategy;

public class InterestCalculator {

    private InterestCalculationStrategy strategy;

    public InterestCalculator(InterestCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(double principal, double rate, int months) {
        return strategy.calculateInterest(principal, rate, months);
    }
}
