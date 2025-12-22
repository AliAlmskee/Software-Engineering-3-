package com.main.services;

import com.main.entity.AccountType;
import com.main.strategy.InterestCalculationStrategy;
import com.main.strategy.InterestCalculator;
import com.main.strategy.InterestStrategyFactory;
import org.springframework.stereotype.Service;

@Service
public class InterestService {

    public double calculateInterest(AccountType accountType,
                                    double principal,
                                    double rate,
                                    int months) {

        InterestCalculationStrategy strategy =
                InterestStrategyFactory.getStrategy(accountType);

        InterestCalculator calculator =
                new InterestCalculator(strategy);

        return calculator.calculate(principal, rate, months);
    }
}
