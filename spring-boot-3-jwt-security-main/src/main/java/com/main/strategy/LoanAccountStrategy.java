package com.main.strategy;

public class LoanAccountStrategy implements InterestCalculationStrategy {

    @Override
    public double calculateInterest(double principal, double rate, int months) {
        return principal * rate * months / 12;
    }
}
