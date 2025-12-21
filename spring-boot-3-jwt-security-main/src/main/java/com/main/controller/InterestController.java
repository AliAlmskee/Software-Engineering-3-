package com.main.controller;

import com.main.dto.InterestCalculationRequest;
import com.main.dto.InterestCalculationResponse;
import com.main.services.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interest")
@RequiredArgsConstructor
public class InterestController {

    private final InterestService interestService;

    @PostMapping("/calculate")
    public InterestCalculationResponse calculateInterest(
            @RequestBody InterestCalculationRequest request) {

        double interest = interestService.calculateInterest(
                request.getAccountType(),
                request.getPrincipal(),
                request.getRate(),
                request.getDurationInMonths()
        );

        return new InterestCalculationResponse(interest);
    }
}
