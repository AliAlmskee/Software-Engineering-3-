package com.main.controller;


import com.main.dto.AccountCreateDto;
import com.main.entity.Account;
import com.main.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody AccountCreateDto dto) {
        return accountService.createAccount(dto);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/tax/{id}")
    public Map<String, Object> getTaxForAccount(@PathVariable Long id) {
        double tax = accountService.calculateTaxForAccount(id);
        return Map.of(
                "accountId", id,
                "tax", tax
        );
    }

    @PostMapping("/tax/group")
    public Map<String, Object> getTaxForGroup(@RequestBody List<Long> accountIds) {
        double tax = accountService.calculateTaxForAccountGroup(accountIds);
        return Map.of(
                "accountIds", accountIds,
                "groupTax", tax
        );
    }
}
