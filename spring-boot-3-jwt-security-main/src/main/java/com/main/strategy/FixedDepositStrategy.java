package com.main.strategy;

public class FixedDepositStrategy implements InterestCalculationStrategy {

    @Override
    public double calculateInterest(double principal, double rate, int months) {
        return principal * Math.pow(1 + rate, months / 12.0) - principal;
    }
}
