package com.main.services;

import com.main.composite.AccountGroup;
import com.main.composite.AccountTax;
import com.main.dto.AccountCreateDto;
import com.main.entity.Account;
import com.main.composite.TaxableAccountComponent;
import com.main.entity.User;
import com.main.repository.AccountRepository;
import com.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @CacheEvict(value = "accounts", allEntries = true)
    public Account createAccount(AccountCreateDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Account account = Account.builder()
                .status(dto.getStatus())
                .type(dto.getType())
                .amount(dto.getAmount())
                .user(user)
                .build();

        return accountRepository.save(account);
    }

    @Cacheable("accounts")
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

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
