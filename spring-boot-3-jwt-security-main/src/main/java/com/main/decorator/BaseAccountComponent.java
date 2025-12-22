package com.main.decorator;

import com.main.entity.Account;

public class BaseAccountComponent implements AccountComponent {

    protected final Account account;

    public BaseAccountComponent(Account account) {
        this.account = account;
    }

    @Override
    public double getAmount() {
        return account.getAmount();
    }
}
