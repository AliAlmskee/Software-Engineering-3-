package com.main.services;

import com.main.decorator.*;
import com.main.entity.Account;
import com.main.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountFeatureService {

    private final AccountRepository repository;

    public AccountFeatureService(AccountRepository repository) {
        this.repository = repository;
    }

    public double getAvailableBalance(Long accountId, boolean premium, boolean overdraft) {

        Account account = repository.findById(accountId)
                .orElseThrow();

        AccountComponent component = new BaseAccountComponent(account);

        if (premium) {
            component = new PremiumAccountDecorator(component);
        }

        if (overdraft) {
            component = new OverdraftAccountDecorator(component);
        }

        return component.getAmount();
    }
}
