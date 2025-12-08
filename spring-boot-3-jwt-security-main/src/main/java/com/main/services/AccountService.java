package com.main.services;

import com.main.composite.AccountGroup;
import com.main.composite.AccountTax;
import com.main.entity.Account;
import com.main.interfaces.TaxableAccountComponent;
import com.main.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
