package com.main.strategy;


import com.main.entity.AccountType;

public class InterestStrategyFactory {

    private InterestStrategyFactory() {
    }

    public static InterestCalculationStrategy getStrategy(AccountType accountType) {

        switch (accountType) {

            case SAVINGS:
                return new SavingsAccountStrategy();

            case CHECKING:
                return new FixedDepositStrategy();

            case INVESTMENT:
                return new LoanAccountStrategy();

            default:
                throw new IllegalArgumentException(
                        "Unsupported account type: " + accountType
                );
        }
    }
}
