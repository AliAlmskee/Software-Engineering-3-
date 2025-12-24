package com.main.dto;

import com.main.entity.AccountType;
import lombok.Data;

@Data
public class InterestCalculationRequest {

    private AccountType accountType;
    private double principal;
    private double rate;
    private int durationInMonths;
}
