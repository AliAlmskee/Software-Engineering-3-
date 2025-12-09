package com.main.controller;


import com.main.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService taxService;

    @GetMapping("/tax/{id}")
    public Map<String, Object> getTaxForAccount(@PathVariable Long id) {
        double tax = taxService.calculateTaxForAccount(id);
        return Map.of(
                "accountId", id,
                "tax", tax
        );
    }

    @PostMapping("/tax/group")
    public Map<String, Object> getTaxForGroup(@RequestBody List<Long> accountIds) {
        double tax = taxService.calculateTaxForAccountGroup(accountIds);
        return Map.of(
                "accountIds", accountIds,
                "groupTax", tax
        );
    }
}
