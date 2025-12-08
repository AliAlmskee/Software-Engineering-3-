package com.main.composite;

import com.main.entity.Account;
import com.main.interfaces.TaxableAccountComponent;

public class AccountTax implements TaxableAccountComponent {

    private final Account account;

    public AccountTax(Account account) {
        this.account = account;
    }

    @Override
    public double getBalance() {
        return account.getAmount();
    }

    @Override
    public double getTax() {
        return switch (account.getType()) {
            case SAVINGS -> getBalance() * 0.05;
            case CHECKING -> getBalance() * 0.10;
            case INVESTMENT -> getBalance() * 0.20;
            default -> 0;
        };
    }
}
