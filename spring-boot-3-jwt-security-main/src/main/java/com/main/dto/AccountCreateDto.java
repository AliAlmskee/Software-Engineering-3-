package com.main.dto;

import com.main.entity.AccountStatus;
import com.main.entity.AccountType;
import lombok.Data;

@Data
public class AccountCreateDto {

    private AccountStatus status;
    private AccountType type;
    private Long userId;
    private double amount;
}
