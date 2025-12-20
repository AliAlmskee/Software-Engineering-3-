package com.main.services;

import com.main.composite.AccountGroup;
import com.main.composite.AccountTax;
import com.main.entity.Account;
import com.main.composite.TaxableAccountComponent;
import com.main.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public double calculateTaxForAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        TaxableAccountComponent leaf = new AccountTax(account);
        return leaf.getTax();
    }

    public double calculateTaxForAccountGroup(List<Long> accountIds) {

        AccountGroup group = new AccountGroup();

        List<Account> accounts = accountRepository.findAllById(accountIds);

        if (accounts.isEmpty()) {
            throw new IllegalArgumentException("No accounts found for provided IDs");
        }

        for (Account account : accounts) {
            group.add(new AccountTax(account));
        }

        return group.getTax();
    }
}
